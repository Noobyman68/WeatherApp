import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.net.http.HttpClient;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class weatherAPI{
  private static String readFile(String fileName){
    String data = "";
    try {
      File file = new File(fileName);
      Scanner sc = new Scanner(file);
      data = sc.nextLine();
      sc.close();
    } catch(FileNotFoundException e){
      data = "fail";
    }
    return data;
  }
  public static void main(String[] args){
    String API_KEY = readFile("weather_API.txt");
    if(API_KEY.equals("fail")){
      System.out.println("File not found");
      return;
    }
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter a city");
    String city = sc.nextLine();
    sc.close();
    HttpRequest request = HttpRequest.newBuilder()
      .uri(URI.create("http://api.weatherapi.com/v1/current.json"))
      .header("key", API_KEY)
      .header("q", city)
      .method("GET", HttpRequest.BodyPublishers.noBody())
      .build();
    HttpResponse<String> response = null;
    try{
      response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
      System.out.println("this works");
    }catch (IOException e){
      System.out.println(e.getMessage());
    }catch (InterruptedException e){
      System.out.println(e.getMessage());
    }
    System.out.println(response.body());
  }
}
