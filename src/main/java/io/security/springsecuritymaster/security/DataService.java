package io.security.springsecuritymaster.security;


import io.security.springsecuritymaster.controller.Account;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DataService {
    
    @PreFilter("filterObject.owner == authentication.name")
    public List<Account> wirteList(List<Account> accounts) {
        return accounts;
    }
    
    
    @PreFilter("filterObject.value.owner == authentication.name")
    public Map<String, Account> wirteMap(Map<String, Account> accounts) {
        return accounts;
    }
    
    @PostFilter("filterObject.owner == authentication.name")
    public List<Account> readList() {
        return new ArrayList<>(List.of(
                new Account("user", false),
                new Account("db", false),
                new Account("admin", false))
        );
    }
    
    @PostFilter("filterObject.value.owner == authentication.name")
    public Map<String,Account> readMap() {
        Map<String, Account> map = Map.of("user", new Account("user", false),
                "db", new Account("db", false),
                "admin", new Account("admin", false)
        );
        return new HashMap<>(map);
    }
}
