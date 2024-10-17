import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        List<Integer> people = readFile("input.txt");
        // System.out.println("people = " + people);
        int d = readMaxWeight("input.txt");
        //  System.out.println("d = " + d);
        int count = countBoats(people, d);
        System.out.println("count = " + count);
    }

    private static int countBoats(List<Integer> people, int d) {
        int count = 0;
        //Упорядочить по весу
        people.sort(Comparator.naturalOrder());
        //  System.out.println("people после сортировки = " + people);
        //В цикле пробовать усаживать самого легкого и самого тяжелого
        int heavyN = people.size() - 1;
        int lightN = 0;
        while (lightN <= heavyN) {
            if (people.get(lightN) + people.get(heavyN) <= d && lightN != heavyN) {
                lightN++;
            }
            heavyN--;
            count++;
        }
        return count;
    }

    private static int readMaxWeight(String fname) {
        int d = -9999;
        try (Scanner scan = new Scanner(new File(fname))) {
            scan.nextInt();
            d = scan.nextInt();
        } catch (FileNotFoundException ex) {
            System.out.println("Ой-ой файл не работает");
        }
        return d;
    }

    private static List<Integer> readFile(String fname) {
        ArrayList<Integer> list = new ArrayList<>();
        try (Scanner scan = new Scanner(new File(fname))) {
            //пропустить 1 строчку файла
            scan.nextLine();
            String s = scan.nextLine();
            String[] mas = s.split(" ");
            //в цикле превратить каждый элемент в число и добавить в список
            for (String el : mas) {
                int chislo = Integer.parseInt(el);
                list.add(chislo);
            }
            // list.addAll( Arrays.stream(mas).map(el->Integer.parseInt(el)).toList());
        } catch (FileNotFoundException ex) {
            System.out.println("Ой-ой файл не работает");
        }
        //    System.out.println("list = " + list);
        return list;
    }
}