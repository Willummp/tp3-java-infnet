package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class Main {

    private static final String API_URL = "https://jsonplaceholder.typicode.com/todos";

    public static void main(String[] args) {
        try {
            // Visualizanddo lista de tarefas
            viewTasks();

            // Inserir  nova tarefa
            insertTask("Nova Tarefa");

            // Selecionando yna tarefa para ver mais detalhes
            selectTask(1);

            // Marcar uma tarefa como concluída
            markTaskAsCompleted(1);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void viewTasks() throws IOException {
        URL url = new URL(API_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = reader.readLine()) != null) {
                response.append(inputLine);
            }
            reader.close();

            JSONArray tasks = new JSONArray(response.toString());
            for (int i = 0; i < tasks.length(); i++) {
                JSONObject task = tasks.getJSONObject(i);
                if (!task.getBoolean("completed")) {
                    System.out.println("Task ID: " + task.getInt("id"));
                    System.out.println("Title: " + task.getString("title"));
                    System.out.println("Completed: " + task.getBoolean("completed")); // Aqui estava o erro
                    System.out.println();
                }
            }
        }
    }


    public static void insertTask(String title) throws IOException {
        // Implementação da inserção de uma nova tarefa na API
        System.out.println("Inserindo nova tarefa: " + title);
    }

    public static void selectTask(int taskId) throws IOException {
        URL url = new URL(API_URL + "/" + taskId);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = reader.readLine()) != null) {
                response.append(inputLine);
            }
            reader.close();

            JSONObject task = new JSONObject(response.toString());
            System.out.println("Task ID: " + task.getInt("id"));
            System.out.println("Title: " + task.getString("title"));
            System.out.println("Completed: " + task.getBoolean("completed"));
            System.out.println();
        }
    }

    public static void markTaskAsCompleted(int taskId) {
        // Implementação da marcação de uma tarefa como concluída (localmente)
        System.out.println("Marcando tarefa " + taskId + " como concluída...");
    }
}
