package com.gdsc.hackerthon.user.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
class UserRivalId implements Serializable {
    @Column(name = "to_user")
    private Long toUserId;
    @Column(name = "from_user")
    private Long fromUserId;
}

@IdClass(UserRivalId.class)
@Entity(name = "user_rival")
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class UserRival {
    @Id
    private Long toUserId;

    @Id
    private Long fromUserId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "to_user", insertable = false, updatable = false)
    private User toUser;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "from_user", insertable = false, updatable = false)
    private User fromUser;
}
