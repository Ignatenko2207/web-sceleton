package info.sjd.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Item {

    private Integer id;
    private String itemCode;
    private String name;
    private Integer price;

    public void clone(Item item){
        this.id = item.id;
        this.itemCode = item.itemCode;
        this.name = item.name;
        this.price = item.price;
    }

}
