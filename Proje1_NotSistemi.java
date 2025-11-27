  import java.util.Scanner;
public class Proje1{
    
   

 

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Öğrenci Not Değerlendirme Sistemi ---");

        // 1. Kullanıcıdan Notları Alma
        System.out.print("Vize Notunu Giriniz: ");
        double vize = scanner.nextDouble();

        System.out.print("Final Notunu Giriniz: ");
        double finalNotu = scanner.nextDouble();

        System.out.print("Ödev Notunu Giriniz: ");
        double odev = scanner.nextDouble();

        // 2. Ortalama Hesaplama
        double ortalama = calculateAverage(vize, finalNotu, odev);

        // 3. Sonuçları Raporlama
        System.out.println("\n--- Değerlendirme Raporu ---");
        System.out.printf("Not Ortalaması: %.2f%n", ortalama);
        System.out.println("Harf Notu: " + getLetterGrade(ortalama));

        // Geçme Durumu Kontrolü
        if (isPassingGrade(ortalama)) {
            System.out.println("Durum: GEÇTİ");
            
            // Onur Listesi Kontrolü (Sadece geçenler için mantıklıdır)
            if (isHonorList(ortalama, vize, finalNotu, odev)) {
                System.out.println("*** TEBRİKLER! ONUR LİSTESİNDESİNİZ ***");
            }
        } else {
            System.out.println("Durum: KALDI");
            
            // Bütünleme Hakkı Kontrolü
            if (hasRetakeRight(ortalama)) {
                System.out.println("Bilgi: Bütünleme sınavına girme hakkınız var.");
            } else {
                System.out.println("Bilgi: Ortalamanız yetersiz, dersi tekrar almalısınız.");
            }
        }

        scanner.close();
    }

    // --- ZORUNLU METOTLAR ---

    // 1. Ortalama hesaplama: Vize %30 + Final %40 + Ödev %30
    public static double calculateAverage(double vize, double finalNotu, double odev) {
        return (vize * 0.30) + (finalNotu * 0.40) + (odev * 0.30);
    }

    // 2. Geçme/kalma kontrolü (Ortalama >= 50)
    public static boolean isPassingGrade(double ortalama) {
        return ortalama >= 50;
    }

    // 3. Harf notu belirleme (Standart bir skala kullanılmıştır)
    public static char getLetterGrade(double ortalama) {
        if (ortalama >= 90) {
            return 'A';
        } else if (ortalama >= 80) {
            return 'B';
        } else if (ortalama >= 70) {
            return 'C';
        } else if (ortalama >= 60) {
            return 'D';
        } else if (ortalama >= 50) {
            return 'E';
        } else {
            return 'F';
        }
    }

    // 4. Onur listesi kontrolü (Ortalama >= 85 VE tüm notlar >= 70)
    public static boolean isHonorList(double ortalama, double vize, double finalNotu, double odev) {
        return (ortalama >= 85) && (vize >= 70) && (finalNotu >= 70) && (odev >= 70);
    }

    // 5. Bütünleme hakkı kontrolü (40 <= Ortalama < 50)
    public static boolean hasRetakeRight(double ortalama) {
        return (ortalama >= 40) && (ortalama < 50);
    }
}

