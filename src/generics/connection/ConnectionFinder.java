package generics.connection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConnectionFinder {

    public HashMap<String, HashMap<String, Boolean>> connections = new HashMap<>();

    public void addAll(List<Connection> connections) {
        for (Connection connection : connections) {
            add(connection);
        }
    }

    public void add(Connection connection) {
        String from = connection.getFrom();
        String to = connection.getTo();
        compute(from, to);
        compute(to, from);
    }

    public boolean hasConnection(String a, String b) {
        return !findConnection(a, b).isEmpty();
    }

    public List<String> findConnection(String a, String b) {
        List<String> result = find(a, b);
        for (String vertex : connections.keySet()) {
            HashMap<String, Boolean> vertexTarget = connections.get(vertex);
            for (String target : vertexTarget.keySet()) {
                vertexTarget.replace(target, false);
            }
        }
        return result;
    }

    private List<String> find(String a, String b) {
        if (!(connections.containsKey(a) && connections.containsKey(b))) {
            return List.of();
        }
        HashMap<String, Boolean> sourceConnections = connections.get(a);
        ArrayList<String> result = new ArrayList<>(List.of(a));

        for (String connection : sourceConnections.keySet()) {
            if (connection.equals(b)) {
                result.add(b);
                return result;
            }
            if (connections.get(a).get(connection).equals(true)) {
                continue;
            }
            connections.get(a).replace(connection, true);
            List<String> partialPath = find(connection, b);
            if (partialPath.contains(b)) {
                result.addAll(partialPath);
                return result;
            }
        }
        return List.of();
    }

    private void compute(String source, String target) {
        connections.compute(source, (String key, HashMap<String, Boolean> value) -> {
            if (value != null) {
                value.put(target, false);
                return value;
            }
            return new HashMap<>(Map.of(target, false));
        });
    }

}