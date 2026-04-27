package com.ayushkumar.journalApp.Entity;

import com.ayushkumar.journalApp.Enum.Sementic;
import com.mongodb.lang.NonNull;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Document(collection = "journal_entries")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JournalEntity {

    @Id
    private ObjectId id;
    @NonNull
    private String title;
    @Field(name = "my_content")
    private String content;
    private LocalDateTime dateTime;

    private Sementic sementic;


}
