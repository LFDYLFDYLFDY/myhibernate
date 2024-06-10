package ru.lfdy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class App4 {
    public static void main(String[] args) throws IOException {
        String filename="my.txt";
        Path pathToFile = Paths.get(filename).toAbsolutePath();
        System.out.println(pathToFile);

//   String sql = Files.lines(Paths.get("C:\\Users\\lfdyl\\IdeaProjects\\myhibernate\\src\\main\\resources\\my.txt")).collect(Collectors.joining(" "));
   String sql = Files.lines(Paths.get("src\\\\main\\\\resources\\\\my.txt")).collect(Collectors.joining(" "));
//    String sql = Files.lines(Paths.get(filename)).collect(Collectors.joining(" "));
//        String sql = Files.lines(Paths.get("create_table_2.sql")).toString();
//            String sql = "select version()";
        System.out.printf(sql);
    }
}
