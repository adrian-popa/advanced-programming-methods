package View;

import Model.Cube;
import Model.Cylinder;
import Model.IObject;

import Controller.Controller;
import Model.Sphere;
import Repository.Repository;

public class View {

    private static final IObject object1 = new Cube(17);
    private static final IObject object2 = new Cylinder(32);
    private static final IObject object3 = new Sphere(25);
    private static final IObject object4 = new Cube(28);
    private static final IObject object5 = new Cylinder(36);

    public static void main(String[] args) {
        Repository repository = new Repository();
        Controller controller = new Controller(repository);

        try {
            controller.addObject(View.object1);
            controller.addObject(View.object2);
            controller.addObject(View.object3);
            controller.addObject(View.object4);
            controller.addObject(View.object5);
            System.out.println("Added a total of 5 objects\n.");

            System.out.println("Object with a volume larger than 25:");
            System.out.println(controller.getByVolume(25));

            controller.removeObject(1);
            System.out.println("Removed object 2.\n");

            System.out.println("Object with a volume larger than 25:");
            System.out.println(controller.getByVolume(25));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
