public class Main {
    public static void main(String[] args) {
        String hpoID = "HP_0500015";
        JenaSparQL jenaSparQL = new JenaSparQL(hpoID);
        jenaSparQL.executeQuery();
        System.out.println(jenaSparQL.getParents());
        System.out.println(jenaSparQL.getSubClasses());

        System.out.println("End of main");
    }
}
