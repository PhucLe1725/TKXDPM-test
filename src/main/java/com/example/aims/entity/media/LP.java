package com.example.aims.entity.media;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Date;

@Data
@Entity
@NoArgsConstructor
@Table(name = "lps")
public class LP extends Media{
    private String artist;
    private String recordLabel;
    private String musicType;
    private String trackList;
    private Date releasedDate;
    public LP(Media media) {
        super(media);
    }
}
