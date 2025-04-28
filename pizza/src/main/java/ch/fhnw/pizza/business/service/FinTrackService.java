package ch.fhnw.fintrack.business.service;

import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class FinTrackService {

    // Simulate admin credentials
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "password";

    // In-memory news store for demo purposes
    private final Map<Integer, String> newsArticles = new HashMap<>();

    // In-memory stock/crypto data store for demo (ID -> Map of TimeRange -> List of values)
    private final Map<String, Map<String, List<Double>>> assetData = new HashMap<>();

    public FinTrackService() {
        // Seed with sample news
        newsArticles.put(1, "Bitcoin hits all-time high amid market optimism.");
        newsArticles.put(2, "Tesla stock jumps 12% after earnings surprise.");
        newsArticles.put(3, "Ethereum 2.0: What you need to know.");

        // Sample data for assets
        assetData.put("BTC", Map.of(
            "1D", List.of(65000.0, 65200.0, 65500.0),
            "1W", List.of(60000.0, 61000.0, 62000.0, 64000.0, 65500.0)
        ));

        assetData.put("TSLA", Map.of(
            "1D", List.of(700.0, 710.0, 720.0),
            "1W", List.of(650.0, 660.0, 675.0, 700.0, 720.0)
        ));
    }

    // UC-4 Authenticate Admin
    public boolean authenticateAdmin(String username, String password) {
        return ADMIN_USERNAME.equals(username) && ADMIN_PASSWORD.equals(password);
    }

    // UC-5 Access Public Pages
    public List<String> getPublicPages() {
        return List.of("Home", "Markets", "News", "About");
    }

    // UC-6 View Stock/Crypto News
    public List<String> getNewsSummaries() {
        return new ArrayList<>(newsArticles.values());
    }

    // UC-7 Read a News Article
    public String getArticleById(int id) {
        return newsArticles.getOrDefault(id, "Article not found.");
    }

    // UC-8 Visualize Stock/Crypto Data
    public List<Double> getAssetData(String assetSymbol, String timeRange) {
        return assetData.getOrDefault(assetSymbol, Map.of())
                        .getOrDefault(timeRange, List.of());
    }

    // UC-9 Adjust Time Range
    public Set<String> getAvailableTimeRanges(String assetSymbol) {
        return assetData.getOrDefault(assetSymbol, Map.of()).keySet();
    }

    // UC-10 Compare Multiple Stocks/Cryptos
    public Map<String, List<Double>> compareAssets(List<String> assetSymbols, String timeRange) {
        Map<String, List<Double>> comparison = new HashMap<>();
        for (String symbol : assetSymbols) {
            List<Double> values = assetData.getOrDefault(symbol, Map.of())
                                           .getOrDefault(timeRange, List.of());
            comparison.put(symbol, values);
        }
        return comparison;
    }
} 
