package tasks;

import common.Person;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
А теперь о горьком
Всем придется читать код
А некоторым придется читать код, написанный мною
Сочувствую им
Спасите будущих жертв, и исправьте здесь все, что вам не по душе!
P.S. функции тут разные и рабочие (наверное), но вот их понятность и эффективность страдает (аж пришлось писать комменты)
P.P.S Здесь ваши правки желательно прокомментировать (можно на гитхабе в пулл реквесте)
 */
public class Task8 {

  private long count;

  //Не хотим выдывать апи нашу фальшивую персону, поэтому конвертим начиная со второй
  public List<String> getNames(List<Person> persons) {
    return persons.stream().skip(1).map(Person::getFirstName).collect(Collectors.toList());
  }

  //ну и различные имена тоже хочется
  public Set<String> getDifferentNames(List<Person> persons) {
    //  distinct() был лишним, так как в Set и так уникальные значения,
    // остается преобразование List к Set, которое можно выполнить и без стримов
    return new HashSet<>(getNames(persons));
  }

  //Для фронтов выдадим полное имя, а то сами не могут
  public String convertPersonToString(Person person) {
    // создаем стрим из полей, содержащих полное имя, фильтруем null, конкатенируем с разделителем-пробелом
    //  PS в оригинальной программе два раза вызывался getSecondName вместо getMiddleName
    return Stream.of(person.getSecondName(), person.getFirstName(), person.getMiddleName())
        .filter(Objects::nonNull).collect(Collectors.joining(" "));
  }

  // словарь id персоны -> ее имя
  public Map<Integer, String> getPersonNames(Collection<Person> persons) {
    // собираем коллекцию HashMap, беря за ключ - id персоны, а за значение - имя.
    // HasMap зачем-то был размером 1
    return persons.stream()
        .collect(Collectors.toMap(Person::getId, this::convertPersonToString, (v1, v2) -> v1, HashMap::new));
  }

  // есть ли совпадающие в двух коллекциях персоны?
  public boolean hasSamePersons(Collection<Person> persons1, Collection<Person> persons2) {
    // disjoint проверяет есть ли у коллекций пересечения
    return !Collections.disjoint(persons1, persons2);
  }

  //...
  public long countEven(Stream<Integer> numbers) {
    return numbers.filter(num -> num % 2 == 0).count();
  }
}
