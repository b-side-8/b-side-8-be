package Bside.Dreamers.domin;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue
    @Column(name = "category_no")
    private Long no;

    private String category_nm;


    @OneToOne(mappedBy = "category",fetch = FetchType.LAZY) //mappedBy : Category쪽에서는 객체 관리 불가능
    private Bucket bucket;


}
