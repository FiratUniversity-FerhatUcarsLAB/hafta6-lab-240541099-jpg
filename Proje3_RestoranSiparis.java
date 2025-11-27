/**
 * Ad Soyad: [Zübeyir Aslan]
 * Öğrenci No: 240541099
 * Proje: RestoranSiparisSistemi
 * Tarih: [27.11.2025]
 */





import java.util.Scanner;

public class RestoranSiparisSistemi {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("--- LEZZET DURAĞI SİPARİŞ SİSTEMİ ---");

        // 1. Kullanıcı ve Zaman Bilgileri
        System.out.print("Saat kaç? (0-23 arası tam sayı): ");
        int saat = scanner.nextInt();

        System.out.print("Öğrenci misiniz? (Evet için true, Hayır için false): ");
        boolean ogrenci = scanner.nextBoolean();

        // 2. Menü Seçimleri
        System.out.println("\n--- ANA YEMEKLER ---");
        System.out.println("1-Izgara Tavuk (85 TL)\n2-Adana Kebap (120 TL)\n3-Levrek (110 TL)\n4-Mantı (65 TL)\n0-Seçim Yok");
        System.out.print("Seçiminiz: ");
        int anaYemekSecim = scanner.nextInt();

        System.out.println("\n--- BAŞLANGIÇLAR ---");
        System.out.println("1-Çorba (25 TL)\n2-Humus (45 TL)\n3-Sigara Böreği (55 TL)\n0-Seçim Yok");
        System.out.print("Seçiminiz: ");
        int baslangicSecim = scanner.nextInt();

        System.out.println("\n--- İÇECEKLER ---");
        System.out.println("1-Kola (15 TL)\n2-Ayran (12 TL)\n3-Meyve Suyu (35 TL)\n4-Limonata (25 TL)\n0-Seçim Yok");
        System.out.print("Seçiminiz: ");
        int icecekSecim = scanner.nextInt();

        System.out.println("\n--- TATLILAR ---");
        System.out.println("1-Künefe (65 TL)\n2-Baklava (55 TL)\n3-Sütlaç (35 TL)\n0-Seçim Yok");
        System.out.print("Seçiminiz: ");
        int tatliSecim = scanner.nextInt();

        // 3. Fiyat Hesaplamaları
        double anaYemekFiyat = getMainDishPrice(anaYemekSecim);
        double baslangicFiyat = getAppetizerPrice(baslangicSecim);
        double icecekFiyat = getDrinkPrice(icecekSecim);
        double tatliFiyat = getDessertPrice(tatliSecim);

        // Happy Hour Kontrolü (Sadece içeceği etkiler)
        if (isHappyHour(saat) && icecekFiyat > 0) {
            System.out.println(">>> Happy Hour Yakaladınız! İçecekte %20 indirim uygulandı.");
            icecekFiyat = icecekFiyat * 0.80; // %20 indirim
        }

        // Ara Toplam
        double toplamTutar = anaYemekFiyat + baslangicFiyat + icecekFiyat + tatliFiyat;

        // Combo Kontrolü
        boolean anaVar = anaYemekFiyat > 0;
        boolean icecekVar = icecekFiyat > 0;
        boolean tatliVar = tatliFiyat > 0;
        boolean isCombo = isComboOrder(anaVar, icecekVar, tatliVar);

        // Genel İndirim Hesaplama
        double indirimTutari = calculateDiscount(toplamTutar, isCombo, ogrenci, saat);
        double odenecekTutar = toplamTutar - indirimTutari;

        // Bahşiş
        double bahsis = calculateServiceTip(odenecekTutar);

        // 4. FİŞ YAZDIRMA
        System.out.println("\n--------------------------------");
        System.out.println("          SİPARİŞ FİŞİ          ");
        System.out.println("--------------------------------");
        System.out.printf("Ana Yemek  : %.2f TL%n", anaYemekFiyat);
        System.out.printf("Başlangıç  : %.2f TL%n", baslangicFiyat);
        System.out.printf("İçecek     : %.2f TL%n", icecekFiyat);
        System.out.printf("Tatlı      : %.2f TL%n", tatliFiyat);
        System.out.println("--------------------------------");
        System.out.printf("Toplam     : %.2f TL%n", toplamTutar);
        System.out.printf("İndirim    : -%.2f TL%n", indirimTutari);
        System.out.println("--------------------------------");
        System.out.printf("NET TUTAR  : %.2f TL%n", odenecekTutar);
        System.out.printf("Önerilen Bahşiş (%%10): %.2f TL%n", bahsis);
        System.out.println("--------------------------------");
        
        scanner.close();
    }

    // --- ZORUNLU METOTLAR ---

    // 1. Ana Yemek Fiyatı
    public static double getMainDishPrice(int secim) {
        switch (secim) {
            case 1: return 85.0;  // Izgara Tavuk
            case 2: return 120.0; // Adana Kebap
            case 3: return 110.0; // Levrek
            case 4: return 65.0;  // Mantı
            default: return 0.0;
        }
    }

    // 2. Başlangıç Fiyatı
    public static double getAppetizerPrice(int secim) {
        switch (secim) {
            case 1: return 25.0; // Çorba
            case 2: return 45.0; // Humus
            case 3: return 55.0; // Sigara Böreği
            default: return 0.0;
        }
    }

    // 3. İçecek Fiyatı
    public static double getDrinkPrice(int secim) {
        switch (secim) {
            case 1: return 15.0; // Kola
            case 2: return 12.0; // Ayran
            case 3: return 35.0; // Meyve Suyu
            case 4: return 25.0; // Limonata
            default: return 0.0;
        }
    }

    // 4. Tatlı Fiyatı
    public static double getDessertPrice(int secim) {
        switch (secim) {
            case 1: return 65.0; // Künefe
            case 2: return 55.0; // Baklava
            case 3: return 35.0; // Sütlaç
            default: return 0.0;
        }
    }

    // 5. Combo Menü Kontrolü (Ana + İçecek + Tatlı varsa)
    public static boolean isComboOrder(boolean anaVar, boolean icecekVar, boolean tatliVar) {
        return anaVar && icecekVar && tatliVar;
    }

    // 6. Happy Hour Kontrolü (14:00 - 17:00 arası)
    public static boolean isHappyHour(int saat) {
        return saat >= 14 && saat <= 17;
    }

    // 7. Genel İndirim Hesaplama
    public static double calculateDiscount(double tutar, boolean combo, boolean ogrenci, int saat) {
        double toplamIndirim = 0.0;

        // Kural 1: Combo Menü veya 200 TL üzeri indirimi
        if (combo) {
            toplamIndirim += tutar * 0.15; // %15 indirim
        } else if (tutar > 200) {
            toplamIndirim += tutar * 0.10; // %10 indirim
        }

        // Kural 2: Öğrenci İndirimi (Ekstra)
        // Not: Hafta içi kontrolü basitlik için burada saat parametresi üzerinden değil,
        // direkt öğrenci boolean'ı üzerinden varsayıldı. 
        if (ogrenci) {
            toplamIndirim += tutar * 0.10; // Ekstra %10
        }

        return toplamIndirim;
    }

    // 8. Bahşiş Hesaplama
    public static double calculateServiceTip(double tutar) {
        return tutar * 0.10;
    }
}

