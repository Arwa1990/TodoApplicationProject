public class Name {

    public static  String checkName(String name){
        if(name.contains("@") || name.contains(".")){
            return "User";
        }else{
          return name;
        }
    }
}
