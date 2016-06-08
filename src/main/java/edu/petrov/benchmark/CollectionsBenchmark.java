package edu.goit.petrov.module01;

import java.util.*;

/**
 * Created by anton on 6/7/16.
 */
public class CollectionsBenchmark {

    private static int VALUE = -1;
    private int iterations;
    private int size;

    private List<Integer> arrayList = new ArrayList<>();
    private List<Integer> linkedList = new LinkedList<>();
    private Set<Integer> hashSet = new HashSet<>();
    private Set<Integer> treeSet = new TreeSet<>();

    public CollectionsBenchmark(int size, int iterations) {
        doPopulate(arrayList, size);
        doPopulate(linkedList, size);
        doPopulate(hashSet, size);
        doPopulate(treeSet, size);
        this.iterations = iterations;
        this.size = size;
    }

    private void doPopulate(Collection<Integer> collection, int count) {
        for (int i = 0; i < count; i++) {
            collection.add(i);
        }
    }

    private void doListAdd(List<Integer> list) {
        list.add(0, VALUE);
        list.add(list.size() / 2, VALUE);
        list.add(list.size() - 1, VALUE);
    }

    private void doSetAdd(Set<Integer> set) {
        set.add(VALUE);
        set.add(set.size() + 0xFFF);
        set.add(set.size() + 0xF);
    }

    private void doCollectionRemove(Collection<Integer> collection) {
        collection.remove(0);
        collection.remove(collection.size() / 2);
        collection.remove(collection.size() - 1);
    }

    private void doCollectionContains(Collection<Integer> collection) {
        boolean b1 = collection.contains(0);
        boolean b2 = collection.contains(collection.size() / 2);
        boolean b3 = collection.contains(collection.size() - 1);
    }

    private void doListGet(List<Integer> list) {
        Integer i1 = list.get(0);
        Integer i2 = list.get(list.size() / 2);
        Integer i3 = list.get(list.size() - 1);
    }

    private void doListIteratorAdd(List<Integer> list) {
        ListIterator<Integer> iterator = list.listIterator(0);
        iterator.add(VALUE);
        iterator = list.listIterator(list.size() / 2);
        iterator.add(VALUE);
        iterator = list.listIterator(list.size() - 1);
        iterator.add(VALUE);
    }

    private void doListIteratorRemove(List<Integer> list) {
        ListIterator<Integer> iterator = list.listIterator(0);
        iterator.next();
        iterator.remove();
        iterator = list.listIterator(list.size() / 2);
        iterator.next();
        iterator.remove();
        iterator = list.listIterator(list.size() - 1);
        iterator.next();
        iterator.remove();
    }

    private long benchmarkMethodIterations(IBenchmarkMethod method, int iterations) {
        long result = 0;
        for (int i = 0; i < iterations; i++) {
            result += benchmarkMethod(method);
        }
        return Math.round((double) result / iterations);
    }

    private long benchmarkMethod(IBenchmarkMethod method) {
        TimeWatch timeWatch = TimeWatch.start();
        method.run();
        return Math.round(timeWatch.time() / 3.0);
    }

    public long benchmarkArrayListAdd() {
        return benchmarkMethodIterations(()->doListAdd(new ArrayList<>(arrayList)), iterations);
    }

    public long benchmarkLinkedListAdd() {
        return benchmarkMethodIterations(()->doListAdd(new LinkedList<>(linkedList)), iterations);
    }

    public long benchmarkArrayListGet() {
        return benchmarkMethodIterations(()->doListGet(new ArrayList<>(arrayList)), iterations);
    }

    public long benchmarkLinkedListGet() {
        return benchmarkMethodIterations(()->doListGet(new LinkedList<>(linkedList)), iterations);
    }

    public long benchmarkHashSetAdd() {
        return benchmarkMethodIterations(()->doSetAdd(new HashSet<>(hashSet)), iterations);
    }

    public long benchmarkTreeSetAdd() {
        return benchmarkMethodIterations(()->doSetAdd(new TreeSet<>(treeSet)), iterations);
    }

    public long benchmarkArrayListRemove() {
        return benchmarkMethodIterations(()->doCollectionRemove(new ArrayList<>(arrayList)), iterations);
    }

    public long benchmarkLinkedListRemove() {
        return benchmarkMethodIterations(()->doCollectionRemove(new LinkedList<>(linkedList)), iterations);
    }

    public long benchmarkHashSetRemove() {
        return benchmarkMethodIterations(()->doCollectionRemove(new HashSet<>(hashSet)), iterations);
    }

    public long benchmarkTreeSetRemove() {
        return benchmarkMethodIterations(()->doCollectionRemove(new TreeSet<>(treeSet)), iterations);
    }

    public long benchmarkArrayListContains() {
        return benchmarkMethodIterations(()->doCollectionContains(new ArrayList<>(arrayList)), iterations);
    }

    public long benchmarkLinkedListContains() {
        return benchmarkMethodIterations(()->doCollectionContains(new LinkedList<>(linkedList)), iterations);
    }

    public long benchmarkHashSetContains() {
        return benchmarkMethodIterations(()->doCollectionContains(new HashSet<>(hashSet)), iterations);
    }

    public long benchmarkTreeSetContains() {
        return benchmarkMethodIterations(()->doCollectionContains(new TreeSet<>(treeSet)), iterations);
    }

    public long benchmarkArrayListPopulate() {
        return benchmarkMethodIterations(()->doPopulate(new ArrayList<>(arrayList), size), iterations);
    }

    public long benchmarkLinkedListPopulate() {
        return benchmarkMethodIterations(()->doPopulate(new LinkedList<>(linkedList), size), iterations);
    }

    public long benchmarkHashSetPopulate() {
        return benchmarkMethodIterations(()->doPopulate(new HashSet<>(hashSet), size), iterations);
    }

    public long benchmarkTreeSetPopulate() {
        return benchmarkMethodIterations(()->doPopulate(new TreeSet<>(treeSet), size), iterations);
    }

    public long benchmarkLinkedListIteratorAdd() {
        return benchmarkMethodIterations(()->doListIteratorAdd(new LinkedList<>(linkedList)), iterations);
    }

    public long benchmarkLinkedListIteratorRemove() {
        return benchmarkMethodIterations(()->doListIteratorRemove(new LinkedList<>(linkedList)), iterations);
    }

    public long benchmarkArrayListIteratorAdd() {
        return benchmarkMethodIterations(()->doListIteratorAdd(new ArrayList<>(arrayList)), iterations);
    }

    public long benchmarkArrayListIteratorRemove() {
        return benchmarkMethodIterations(()->doListIteratorRemove(new ArrayList<>(arrayList)), iterations);
    }

}
