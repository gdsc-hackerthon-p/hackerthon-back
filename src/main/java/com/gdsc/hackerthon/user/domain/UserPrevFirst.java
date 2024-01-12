package com.gdsc.hackerthon.user.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "prev_first")
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class UserPrevFirst {
    @Id
    @JoinColumn(name = "prev_id")
    @OneToOne
    private User user;
}
