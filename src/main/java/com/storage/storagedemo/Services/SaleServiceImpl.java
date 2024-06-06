package com.storage.storagedemo.Services;

import com.storage.storagedemo.Dto.Requests.ProductUpdateRequestDto;
import com.storage.storagedemo.Dto.Requests.SaleRequestDto;
import com.storage.storagedemo.Dto.Requests.SaleUpdateRequestDto;
import com.storage.storagedemo.Dto.Responses.SaleResponseDto;
import com.storage.storagedemo.Exceptions.CustomExceptions.ResourceNotFoundException;
import com.storage.storagedemo.Logging.SaleTransactionLogger;
import com.storage.storagedemo.Models.JoinTableEntities.SaleProduct;
import com.storage.storagedemo.Models.Product;
import com.storage.storagedemo.Models.Sale;
import com.storage.storagedemo.Repositories.SaleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class SaleServiceImpl implements SaleService{

    private final SaleRepository saleRepository;
    private final ProductService productService;
    private final ClientService clientService;
    private final SaleTransactionLogger saleTransactionLogger;

    @Override
    public List<SaleResponseDto> getAllSales() {
        List<SaleResponseDto> sales = saleRepository.findAllSales();
        return sales;
    }

//    @Override
//    public Sale createSale(SaleRequestDto request) {
//        Sale sale = new Sale();
//        double totalPrice = 0.0;
//
//        for(Integer productId : request.getProductIds()){
//            Product product = productService.getProductById(productId);
//
//            sale.getSaleProducts().add(product);
//            totalPrice += product.getPrice();
//        }
//
//        sale.setTotal(totalPrice);
//        sale.setCreationDate(new Date());
//        sale.setSeller(request.getSeller());
//
//        sale.setClient(clientService.getClientById(request.getClientId()));
//
//        return saleRepository.save(sale);
//    }

    @Override
    public Sale createSale(SaleRequestDto request) {
        Sale sale = new Sale();
        double totalPrice = 0.0;
        List<SaleProduct> saleProducts = new ArrayList<>();

        for (ProductUpdateRequestDto productRequest : request.getProducts()) {
            Product product = productService.getProductById(productRequest.getProductId());

            SaleProduct saleProduct = new SaleProduct();
            saleProduct.setSale(sale);
            saleProduct.setProduct(product);
            saleProduct.setQuantity(productRequest.getQuantity());
            saleProduct.setPrice(productRequest.getPrice());

            saleProducts.add(saleProduct);
            totalPrice += productRequest.getPrice() * productRequest.getQuantity();
        }

        sale.setSaleProducts(saleProducts);
        sale.setTotal(totalPrice);
        sale.setCreationDate(new Date());
        sale.setSeller(request.getSeller());
        sale.setClient(clientService.getClientById(request.getClientId()));

        return saleRepository.save(sale);
    }


    @Override
    public Sale updateSale(Integer saleId, SaleUpdateRequestDto request) {
        Sale sale = saleRepository.findById(saleId)
                .orElseThrow(() -> new ResourceNotFoundException("Sale with id: " + saleId + " not found"));

        // Update existing saleProducts or remove as needed
        Iterator<SaleProduct> iterator = sale.getSaleProducts().iterator();
        while (iterator.hasNext()) {
            SaleProduct existingSaleProduct = iterator.next();
            boolean found = false;
            for (ProductUpdateRequestDto productUpdate : request.getProductUpdates()) {
                if (existingSaleProduct.getProduct().getId().equals(productUpdate.getProductId())) {
                    // Update existing saleProduct
                    existingSaleProduct.setQuantity(productUpdate.getQuantity());
                    existingSaleProduct.setPrice(productUpdate.getPrice());
                    iterator.remove(); // Remove processed saleProduct
                    found = true;
                    break;
                }
            }
            if (!found) {
                iterator.remove(); // Remove saleProduct not found in request
            }
        }

        // Add new saleProducts
        for (ProductUpdateRequestDto productUpdate : request.getProductUpdates()) {
            boolean existingProduct = sale.getSaleProducts().stream()
                    .anyMatch(sp -> sp.getProduct().getId().equals(productUpdate.getProductId()));
            if (!existingProduct) {
                Product product = productService.getProductById(productUpdate.getProductId());
                SaleProduct saleProduct = new SaleProduct();
                saleProduct.setSale(sale);
                saleProduct.setProduct(product);
                saleProduct.setQuantity(productUpdate.getQuantity());
                saleProduct.setPrice(productUpdate.getPrice());
                sale.getSaleProducts().add(saleProduct);
            }
        }

        double totalPrice = sale.getSaleProducts().stream()
                .mapToDouble(sp -> sp.getPrice() * sp.getQuantity())
                .sum();
        sale.setTotal(totalPrice);
        sale.setLastModified(new Date());

        saleTransactionLogger.logUpdate(sale);
        return saleRepository.save(sale);
    }



//    @Override
//    public Sale updateSale(Integer saleId, SaleUpdateRequestDto request) {
//        Sale sale = saleRepository.findById(saleId)
//                .orElseThrow(
//                        () -> new ResourceNotFoundException("Sale with id: " + saleId + " not found")
//                );
//
//        double totalPrice = 0.0;
//
//        for (Integer productId : request.getProductIds()){
//            Product product = productService.getProductById(productId);
//
//            sale.getProducts().replaceAll(product1 -> product);
//
//            totalPrice += product.getPrice();
//        }
//
//        sale.setTotal(totalPrice);
//
//        return saleRepository.save(sale);
//    }
}
