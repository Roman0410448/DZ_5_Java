import java.util.*;

public class PhoneBook {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in,"cp866");
        HashMap<String, HashSet<String>> phoneBook = new HashMap<>();

        // Исходная телефонная книга
        phoneBook.put("Иванов Иван", new HashSet<>(Arrays.asList("21354", "89014562321")));
        phoneBook.put("Петров Андрей", new HashSet<>(Collections.singletonList("28934")));
        phoneBook.put("Сидоров Евгений", new HashSet<>(Arrays.asList("21698", "89056047812", "89612356428")));

        while (true) {
            System.out.println("1. Добавить контакт");
            System.out.println("2. Вывести список контактов");
            System.out.println("3. Выйти");
            System.out.print("Выберите действие: ");
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Некорректный выбор. Повторите попытку");
                System.out.println();
                continue;
            }

            if (choice == 1) {
                System.out.print("Введите имя контакта: ");
                String name = scanner.nextLine();

                if (name.isEmpty()) {
                    System.out.println("Имя контакта не может быть пустым");
                    System.out.println();
                    continue;
                }

                System.out.print("Введите номер телефона: ");
                String phoneNumber = scanner.nextLine();

                while (!isValidPhoneNumber(phoneNumber)) {
                    System.out.println("Некорректный формат номера телефона");
                    System.out.print("Введите номер телефона еще раз или введите '0' чтобы вернуться в меню: ");
                    phoneNumber = scanner.nextLine();

                    if (phoneNumber.equals("0")) {
                        break;
                    }
                }

                if (phoneNumber.equals("0")) {
                    System.out.println();
                    continue;
                }

                HashSet<String> phoneNumbers = phoneBook.getOrDefault(name, new HashSet<>());
                phoneNumbers.add(phoneNumber);
                phoneBook.put(name, phoneNumbers);

                System.out.println("Контакт добавлен");
                System.out.println();
            } else if (choice == 2) {
                if (phoneBook.isEmpty()) {
                    System.out.println("Телефонная книга пуста");
                    System.out.println();
                    continue;
                }

                System.out.println("Список контактов:");
                List<Map.Entry<String, HashSet<String>>> sortedEntries = new ArrayList<>(phoneBook.entrySet());
                Collections.sort(sortedEntries, new Comparator<Map.Entry<String, HashSet<String>>>() {
                    @Override
                    public int compare(Map.Entry<String, HashSet<String>> entry1, Map.Entry<String, HashSet<String>> entry2) {
                        return entry2.getValue().size() - entry1.getValue().size();
                    }
                });

                for (Map.Entry<String, HashSet<String>> entry : sortedEntries) {
                    System.out.println(entry.getKey() + ": " + entry.getValue());
                }

                System.out.println();
            } else if (choice == 3) {
                break;
            } else {
                System.out.println("Некорректный выбор. Повторите попытку");
                System.out.println();
            }
        }
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        // Проверяем формат номера телефона
        
        return phoneNumber.matches("\\d{5,11}");
    }
}