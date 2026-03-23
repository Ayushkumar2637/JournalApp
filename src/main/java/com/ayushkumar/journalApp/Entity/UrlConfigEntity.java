package com.ayushkumar.journalApp.Entity;

import com.mongodb.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "config")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UrlConfigEntity {

    @Id
    private ObjectId id;
    @Indexed(unique = true)
    private String key;
    @NonNull
    private String value;

}
