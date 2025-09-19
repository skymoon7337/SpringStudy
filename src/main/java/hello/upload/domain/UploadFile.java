package hello.upload.domain;

import lombok.Data;

@Data
public class UploadFile {

    private String uploadFileName;  //고객이 저장한 파일 네임
    private String storeFileName;   //실제 저장되는 파일 네임

    public UploadFile(String uploadFileName, String storeFileName) {
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }
}
