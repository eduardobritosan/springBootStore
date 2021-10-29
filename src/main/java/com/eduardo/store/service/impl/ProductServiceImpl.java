package com.eduardo.store.service.impl;

import com.eduardo.store.dto.PriceReductionDTO;
import com.eduardo.store.dto.ProductDTO;
import com.eduardo.store.dto.SupplierDTO;
import com.eduardo.store.enums.ProductStateEnum;
import com.eduardo.store.model.PriceReduction;
import com.eduardo.store.model.Product;
import com.eduardo.store.model.ProductSupplier;
import com.eduardo.store.model.Supplier;
import com.eduardo.store.repo.PriceReductionRepository;
import com.eduardo.store.repo.ProductRepository;
import com.eduardo.store.repo.ProductSupplierRepository;
import com.eduardo.store.repo.SupplierRepository;
import com.eduardo.store.service.IPriceReductionService;
import com.eduardo.store.service.IProductService;
import com.eduardo.store.service.IProductSupplierService;
import com.eduardo.store.service.ISupplierService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

@Transactional
@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private ISupplierService supplierService;

    @Autowired
    private IProductSupplierService productSupplierService;

    @Autowired
    private ProductSupplierRepository productSupplierRepository;

    @Autowired
    private PriceReductionRepository priceReductionRepository;

    @Autowired
    private IPriceReductionService priceReductionService;

    @Autowired
    private ModelMapper modelMapper;

    public List<ProductDTO> findAll() {
        return productRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public List<ProductDTO> findByProductCode(Long productCode) {
        return productRepository.findByProductCode(productCode).stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public List<ProductDTO> findByState(String state) {
        return productRepository.findByState(ProductStateEnum.valueOf(state.toUpperCase(Locale.ROOT))).
                stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public ProductDTO save(ProductDTO product) {
        if(!exists(product)) {
            Date localDate = Date.from(LocalDate.now().atStartOfDay(ZoneId.of("GMT+01:00")).toInstant());
            product.setCreationDate(localDate);
            return convertToDto(productRepository.save(convertToPojo(product)));
        }
        return null;
    }

    public void deleteByProductCode(Long productCode) {
        productRepository.deleteByProductCode(productCode);
    }

    public ProductDTO deactivate(Long productCode) {
        if(existsItemCode(productCode)) {
            ProductDTO foundProductDTO = findByProductCode(productCode).get(0);
            foundProductDTO.setState(ProductStateEnum.DISCONTINUED);
            update(foundProductDTO, productCode);
            return foundProductDTO;
        }
        return null;
    }

    public ProductDTO update(ProductDTO productDTO, Long productCode) {
        if (!Objects.equals(productDTO.getProductCode(), productCode)) {
            throw new IllegalArgumentException();
        }
        try{
            if(exists(productDTO)) {
                Product foundProduct = productRepository.findByProductCode(productCode).get(0);
                BeanUtils.copyProperties(productDTO, foundProduct, "idProduct", "suppliers",
                        "productCode", "priceReductions");
                productRepository.save(foundProduct);
                return convertToDto(foundProduct);
            }
        } catch(EntityExistsException | EntityNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ProductDTO addSupplier(SupplierDTO supplierDTO, Long productCode) {
        if(existsItemCode(productCode) &&
                (supplierRepository.findBySupplierCode(supplierDTO.getSupplierCode()).isEmpty())) {
            Product product = productRepository.findByProductCode(productCode).get(0);
            Supplier supplier = supplierService.convertToPojo(supplierDTO);
            supplierRepository.save(supplier);
            ProductSupplier productSupplier = new ProductSupplier(null, product, supplier);
            productSupplierRepository.save(productSupplier);
            return convertToDto(productRepository.findByProductCode(productCode).get(0));
        }
        return null;
    }

    public ProductDTO addPriceReduction(PriceReductionDTO priceReductionDTO, Long productCode) {
        if(existsItemCode(productCode) &&
                (priceReductionRepository.findByPriceReductionCode(priceReductionDTO.getPriceReductionCode())
                        .isEmpty())) {
            Product product = productRepository.findByProductCode(productCode).get(0);
            if((!checkPriceReductionOverlap(priceReductionDTO, product)) && checkDateValidity(priceReductionDTO)){
                PriceReduction priceReduction = priceReductionService.convertToPojo(priceReductionDTO);
                priceReduction.setProduct(product);
                priceReductionRepository.save(priceReduction);
                return convertToDto(productRepository.findByProductCode(productCode).get(0));
            }
        }
        return null;
    }

    private ProductDTO convertToDto(Product product) {
        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
        if (productDTO.getSuppliers() != null) {
            productDTO.setSuppliers(product.getSuppliers().stream().map(supplier -> productSupplierService.convertToDto(supplier)).collect(Collectors.toList()));
        }
        return productDTO;
    }

    private Product convertToPojo(ProductDTO productDTO) {
        return modelMapper.map(productDTO, Product.class);
    }

    private boolean exists(ProductDTO product) {
        return existsItemCode(product.getProductCode());
    }

    private boolean existsItemCode(Long productCode) {
        return findByProductCode(productCode).size() == 1;
    }

    private boolean checkPriceReductionOverlap(PriceReductionDTO priceReductionDTO, Product product) {
        final boolean[] overlaps = {false};
        product.getPriceReductions().forEach(priceReduction -> {
            if((priceReduction.getStartDate().before(priceReductionDTO.getEndDate()))
            && priceReduction.getEndDate().after(priceReductionDTO.getStartDate())) {
                overlaps[0] = true;
            }
        });
        return overlaps[0];
    }

    private boolean checkDateValidity(PriceReductionDTO priceReductionDTO) {
        return priceReductionDTO.getStartDate().before(priceReductionDTO.getEndDate());
    }
}
