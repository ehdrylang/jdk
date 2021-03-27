package jpabook.jpashop.domain.item;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@Getter @Setter
@DiscriminatorValue("A")
public class Album extends Item {
    @NonNull
    private String artist;
    private String etc;
}
