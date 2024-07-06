import definition.ISet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

class Set<T> implements ISet<T> {

    private final ArrayList<T> array;
    private int count;

    public Set() {
        this.array = new ArrayList<>();
        this.count = 0;
    }

    @Override
    public void add(T a) {
        if (!array.contains(a)) {
            array.add(a);
            count++;
        }
    }

    @Override
    public void remove(T a) {
        if (array.contains(a)) {
            array.remove(a);
            count--;
        }
    }

    @Override
    public boolean isEmpty() {
        return this.count == 0;
    }

    @Override
    public T choose() {
        if (this.count == 0) {
            System.out.println("No se puede elegir un elemento del conjunto vacío");
            return null;
        }
        int randomIndex = (new Random()).nextInt(this.count);
        return array.get(randomIndex);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Set<?> set = (Set<?>) o;
        if (count != set.count) return false;

        List<T> array1Copy = new ArrayList<>(array);
        List<?> array2Copy = new ArrayList<>(set.array);
        Collections.sort((List<Comparable>) array1Copy);
        Collections.sort((List<Comparable>) array2Copy);

        return array1Copy.equals(array2Copy);
    }

    public boolean contains(T a) {
        return array.contains(a);
    }

    public int size() {
        return count;
    }

}