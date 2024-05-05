package io.security.springsecuritymaster.controller;


import io.security.springsecuritymaster.security.DataService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MethodController {
    
    private final DataService dataService;
    
    @PostMapping("/writeList")
    public List<Account> writeList(@RequestBody List<Account> account) {
        return dataService.wirteList(account);
    }
    
    @PostMapping("/writeMap")
    public Map<String, Account> writeMap(@RequestBody List<Account> accounts) {
        
        Map<String, Account> collect = accounts.stream()
                .collect(Collectors.toMap(
                        Account::getOwner,
                        account -> account
                ));
        
        return dataService.wirteMap(collect);
    }
    
    @GetMapping("/readList")
    public List<Account> readList() {
        return dataService.readList();
    }
    @GetMapping("/readMap")
    public Map<String, Account> readMap() {
        return dataService.readMap();
    }
}
