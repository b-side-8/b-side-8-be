package Bside.Dreamers.domin.dto;

import Bside.Dreamers.domin.File;
import Bside.Dreamers.domin.Member;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FileSignupDTO {

    private Long id;
    private String file_stre_cours;
    private String stre_file_name;
    private String originial_file_nm;
    private String file_extsn;
    private Long file_size;
    private String del_yn;
    private LocalDateTime regist_dt;


    public File toEntity(){
        return File.builder()
                .file_stre_cours(file_stre_cours)
                .stre_file_name(stre_file_name)
                .originial_file_nm(originial_file_nm)
                .file_extsn(file_extsn)
                .file_size(file_size)
                .del_yn(del_yn)
                .build();
    }

    @Builder
    public FileSignupDTO(Long id, String file_stre_cours,String stre_file_name, String originial_file_nm, String file_extsn, Long file_size, String del_yn) {
        this.id = id;
        this.file_stre_cours = file_stre_cours;
        this.stre_file_name = stre_file_name;
        this.originial_file_nm = originial_file_nm;
        this.file_extsn = file_extsn;
        this.file_size = file_size;
        this.del_yn = del_yn;
    }


}
