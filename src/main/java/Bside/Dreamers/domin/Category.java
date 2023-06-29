package Bside.Dreamers.domin;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue
    @Column(name = "category_no")
    private Long no;

    private String category_nm;


    @OneToMany(mappedBy = "category",fetch = FetchType.LAZY) //mappedBy : Category쪽에서는 객체 관리 불가능
    private List<Bucket> buckets = new ArrayList<>();


}
