public class SessionManager {
    private static int userId = -1; // Default to -1 (no session)
    private static String username = null;

    public synchronized static void setSession(int id, String user) {
        userId = id;
        username = user;
    }

    public synchronized static int getUserId() {
        return userId;
    }

    public synchronized static String getUsername() {
        return username;
    }

    public static boolean isLoggedIn() {
        return userId != -1;
    }

    public synchronized static void clearSession() {
        userId = -1;
        username = null;
    }
}
