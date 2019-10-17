package Repository;

import Model.IObject;

public class Repository implements IRepository {

    private IObject[] objects = new IObject[50];
    private int length = 0;

    public void addObject(IObject object) throws Exception {
        if (object.getVolume() <= 0) {
            throw new Exception("The volume must have a positive value.");
        }
        if (length == 50) {
            throw new Exception("You have reached the end of the array's capacity.");
        }
        this.objects[length++] = object;
    }

    public void removeObject(int index) throws Exception {
        if (index > length - 1) {
            throw new Exception("Invalid array index, out of bounds.");
        }
        System.arraycopy(this.objects, index + 1, this.objects, index, length - 1 - index);
        length--;
    }

    @Override
    public String getByVolume(int volume) {
        StringBuilder filteredObjects = new StringBuilder();

        for (int i = 0; i < length; i++) {
            IObject object = this.objects[i];
            if (object.getVolume() > volume) {
                filteredObjects.append("Object ").append(i + 1).append(": ").append(object.toString()).append("\n");
            }
        }

        return filteredObjects.toString();
    }
}
