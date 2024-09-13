package org.apache.catalina.session;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class SessionManager {
    private static final SessionManager sessionManager = new SessionManager(new ConcurrentHashMap<>());

    private Map<String, Session> sessions;

    private SessionManager(Map<String, Session> sessions) {
        this.sessions = sessions;
    }

    public static SessionManager getInstance() {
        return sessionManager;
    }

    public Session findSessionById(String sessionId) {
        return sessions.getOrDefault(sessionId, new Session());
    }

    public String storeSession(Session session) {
        String sessionId = UUID.randomUUID().toString();
        sessions.put(sessionId, session);
        return sessionId;
    }
}
