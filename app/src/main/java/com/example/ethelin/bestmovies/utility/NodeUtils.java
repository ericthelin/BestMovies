package com.example.ethelin.bestmovies.utility;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.List;

public class NodeUtils {

    public static List<JsonNode> nodeToList(JsonNode node) {
        List<JsonNode> nodeList = new ArrayList<>();
        if (node != null && node.isArray()) {
            for (int i = 0; i < node.size(); i++) {
                nodeList.add(node.get(i));
            }
        }
        return nodeList;
    }

    public static JsonNode nodeForKeyPath(JsonNode node, String keyPath) {
        for (String key : keyPath.split("\\.")) {
            if (node == null) {
                break;
            } else if (node.isArray()) {
                try {
                    node = nodeAtIndex(node, Integer.valueOf(key));
                } catch (NumberFormatException e) {
                    node = null;
                }
            } else {
                node = node.get(key);
            }
        }
        return node;
    }

    private static JsonNode nodeAtIndex(JsonNode array, int index) {
        if (array.size() >= index + 1) {
            return array.get(index);
        } else {
            return null;
        }
    }

    public static String nodeToString(JsonNode node) {
        if (node == null) {
            return null;
        } else {
            String text = node.asText();
            if (text != null && (text.trim().isEmpty() || text.equals("null"))) {
                return null;
            } else {
                return text;
            }
        }
    }

    public static String nodeToString(JsonNode node, String path) {
        return nodeToString(nodeForKeyPath(node, path));
    }

    public static long nodeToLong(JsonNode node) {
        String raw = nodeToString(node);
        if (raw != null) {
            try {
                return Long.parseLong(raw);
            } catch (NumberFormatException e) {
                // Ignore
            }
        }
        return 0;
    }

    public static double nodeToDouble(JsonNode node) {
        String raw = nodeToString(node);
        if (raw != null) {
            try {
                return Double.parseDouble(raw);
            } catch (NumberFormatException e) {
                // Ignore
            }
        }
        return 0;
    }

    public static float nodeToFloat(JsonNode node) {
        String raw = nodeToString(node);
        if (raw != null) {
            try {
                return Float.parseFloat(raw);
            } catch (NumberFormatException e) {
                // Ignore
            }
        }
        return 0;
    }

    public static int nodeToInt(JsonNode node) {
        String raw = nodeToString(node);
        if (raw != null) {
            try {
                return Integer.parseInt(raw);
            } catch (NumberFormatException e) {
                // Ignore
            }
        }
        return 0;
    }

    public static boolean nodeToBoolean(JsonNode node) {
        String raw = nodeToString(node);
        return raw != null && Boolean.parseBoolean(raw);
    }
}