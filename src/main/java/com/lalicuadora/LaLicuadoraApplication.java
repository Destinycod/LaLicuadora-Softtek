package com.lalicuadora;

import com.lalicuadora.app.data.*;
import com.lalicuadora.app.domain.models.entities.products.*;
import com.lalicuadora.app.domain.models.entities.shops.*;
import com.lalicuadora.app.domain.models.entities.users.Client;
import com.lalicuadora.app.domain.models.entities.users.Seller;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class LaLicuadoraApplication {
    public static void main(String[] args) {
        SpringApplication.run(LaLicuadoraApplication.class, args);
    }

    @Bean
    public CommandLineRunner init(
            BaseProductRepository bProdRepository,
            BaseProdXPossibleCustomRepository baseProdXPossibleCustomRepository,
            CartRepository cartRepository,
            ClientRepository clientRepository,
            CustomizationAreaRepository customizationAreaRepository,
            CustomizationRepository customizationRepository,
            CustomProductRepository customProductRepository,
            ItemRepository itemRepository,
            PossibleCustomizationRepository possibleCustomizationRepository,
            PublicationRepository publicationRepository,
            PublicationStatusRepository publicationStatusRepository,
            PurchaseRepository purchaseRepository,
            SellerRepository sellerRepository,
            ShopRepository shopRepository,
            SpecificCustomizationRepository specificCustomizationRepository
            ) {
        return (args) -> {
            System.out.println("Anda");

            PossibleCustomization possible = new PossibleCustomization();
            PossibleCustomization possible2 = new PossibleCustomization();
            Item item = new Item();
            PublicationStatus publicationStatus= new PublicationStatus();
            //Publication publication = new Publication();
            Seller seller = new Seller("Pepe", "Gonzales");
            Customization customization = new Customization("Logo UTN 4x4", 500.0, "Logo UTN.jpg", seller);            Client client = new Client("Norman", "Diaz");
            SpecificCustomization specificCustomization = new SpecificCustomization();
            CustomProduct customProduct = new CustomProduct();
            Purchase purchase = new Purchase();
            Cart cart = new Cart();
            //Shop shop = new Shop();

            /* --------PRODUCTOS BASE---------------------------------------------*/
            BaseProduct gorraNegra = new BaseProduct("Gorra", 1200.0, "Color negro, Unico", "3 horas");
            bProdRepository.save(gorraNegra);
            BaseProduct gorraRoja = new BaseProduct("Gorra", 2500.0, "Color rojo, Unico", "6 horas");
            bProdRepository.save(gorraRoja);
            BaseProduct gorraBlanca = new BaseProduct("Gorra", 1700.0, "Color blanco, Unico", "7 horas");
            bProdRepository.save(gorraBlanca);
            BaseProduct buzoRojo = new BaseProduct("Buzo", 2500.0, "Color rojo, XL", "6 horas");
            bProdRepository.save(buzoRojo);
            BaseProduct remeraBlanca = new BaseProduct("Remera", 1500.5, "Color blanco, L", "4 horas");
            bProdRepository.save(remeraBlanca);
            BaseProduct zapatillasBlancas = new BaseProduct("Zapatillas", 5000.5, "Color blanco, 38", "8 horas");
            bProdRepository.save(zapatillasBlancas);

            /* --------AREA DE PERSONALIZACION---------------------------------------------*/
            CustomizationArea frenteA = new CustomizationArea("frente - arriba");
            frenteA.getPossibleCustomizations().add(possible);
            customizationAreaRepository.save(frenteA);
            CustomizationArea frenteC = new CustomizationArea("frente - centro");
            frenteC.getPossibleCustomizations().add(possible2);
            customizationAreaRepository.save(frenteC);
            CustomizationArea frenteAb = new CustomizationArea("frente - abajo");
            customizationAreaRepository.save(frenteAb);
            CustomizationArea dorsoA = new CustomizationArea("dorso - arriba");
            customizationAreaRepository.save(dorsoA);
            CustomizationArea dorsoC = new CustomizationArea("dorso - centro");
            customizationAreaRepository.save(dorsoC);

            /* --------POSIBLE PERSONALIZACION---------------------------------------------*/
            possible.getCustomizationTypes().add(CustomizationType.BLACK_AND_WHITE_IMAGE);
            possible.getCustomizationTypes().add(CustomizationType.TEXT);
            possible.setCustomizationArea(frenteA);
            possibleCustomizationRepository.save(possible);

            possible2.getCustomizationTypes().add(CustomizationType.COLOR_IMAGE);
            possible2.getCustomizationTypes().add(CustomizationType.TEXT);
            possible2.setCustomizationArea(frenteC);
            possibleCustomizationRepository.save(possible2);

            /* --------POSIBLE PERSONALIZACION X BASE PRODUCT-------------------------------*/
            BaseProdXPossibleCustom prodXPossibleCustom = new BaseProdXPossibleCustom();
            prodXPossibleCustom.setPossibleCustomization(possible);
            prodXPossibleCustom.setBaseProduct(gorraBlanca);
            baseProdXPossibleCustomRepository.save(prodXPossibleCustom);

            /* --------VENDEDOR------------------------------------------------------
            seller.getCustomization().add(customization);
            seller.getPaymentMethod().add(PaymentMethod.CASH);
            seller.getPaymentMethod().add(PaymentMethod.CREDIT_CARD);
            sellerRepository.save(seller);

             --------PERSONALIZACION------------------------------------------------------
            customization.setSeller(seller);
            customizationRepository.save(customization);

             --------PERSONALIZACION ESPECIFICA--------------------------------------------
            specificCustomization.setCustomizations(customization);
            specificCustomization.setBaseProdXPossibleCustom(prodXPossibleCustom);
            specificCustomization.getCustomProducts().add(customProduct);
            specificCustomizationRepository.save(specificCustomization);

             --------PRODUCTO PERSONALIZADO--------------------------------------------
            customProduct.setSpecificCustomization(specificCustomization);
            customProduct.getItems().add(item);
            //customProduct.getPublications().add(publication);
            customProductRepository.save(customProduct);

            /* --------ITEM--------------------------------------------
            item.setCustomProduct(customProduct);
            item.setPurchase(purchase);
            item.setCart(cart);
            item.setAmount(5);
            itemRepository.save(item);

            /* --------ESTADO PUBLICACION--------------------------------------------
            publicationStatus.getRealStatus().add(RealStatus.PUBLISHED);
            //publicationStatus.setPublication(publication);
            //publicationStatus.setDateTime();
            publicationStatusRepository.save(publicationStatus);

*/



            //REVISAR SPECIFIC CUSTOMIZATION CONTROLLER -> MÉTODO POST
        };
    }
}