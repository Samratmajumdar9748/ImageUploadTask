package com.zyaapar.imageapi;

import javax.persistence.*;

@Entity
@Table(name = "image_entity")
public class ImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String name;

    @Column(length = 500000)
    private byte[] imgBytes;

    public ImageEntity(String name, byte[] imgBytes) {

        this.name = name;
        this.imgBytes = imgBytes;
    }
    public ImageEntity(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getImgBytes() {
        return imgBytes;
    }

    public void setImgBytes(byte[] imgBytes) {
        this.imgBytes = imgBytes;
    }
}
