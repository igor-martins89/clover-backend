package mlclover.appplication.entities.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Tipo {

    /** ENUM que será encarregado de dizer se o Objeto é padrão ou adicional. Exemplo: Endereço padrão que
     o produto ser enviado. Cartão padrão que será cobrado no ato da compra.
      */

    DEFAULT(1, "Padrão"),
    ADITIONAL(2, "Adicional");

    private int cod;
    private String descricao;

    public static Tipo toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }
        for (Tipo x : Tipo.values()) {
            if (cod.equals(x.getCod())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Id inválido: " + cod);
    }

}