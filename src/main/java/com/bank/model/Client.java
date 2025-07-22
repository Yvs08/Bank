package com.bank.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Client {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private String firstName;
    private String email;    
    private String adress;
    private String phoneCall;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Account> accounts;
}
