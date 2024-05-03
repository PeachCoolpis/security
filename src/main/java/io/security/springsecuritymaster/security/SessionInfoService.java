package io.security.springsecuritymaster.security;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.SortedMap;

@Service
@RequiredArgsConstructor
public class SessionInfoService {
    
    
    private final SessionRegistry sessionRegistry;
    
    public void sessionInfo() {
        List<Object> allPrincipals = sessionRegistry.getAllPrincipals();
        for (Object allPrincipal : allPrincipals) {
            List<SessionInformation> allSessions = sessionRegistry.getAllSessions(allPrincipal, false);
            for (SessionInformation allSession : allSessions) {
                String format = String.format("사용자 : %s , 세션ID : %s , 최종 요청 시간 : %s", allPrincipal, allSession.getSessionId(), allSession.getLastRequest());
                System.out.println(format);
                
            }
        }
    }
}
