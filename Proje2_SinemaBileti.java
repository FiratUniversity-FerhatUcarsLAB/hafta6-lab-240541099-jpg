/**
 * Ad Soyad: [Zübeyir Aslan]
 * Öğrenci No: 240541099
 * Proje: SinemaBileti
 * Tarih: [27.11.2025]
 */




import java.util.Scanner;

public class SinemaBiletSistemi {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("--- SİNEMA BİLETİ FİYATLANDIRMA SİSTEMİ ---");

        // GİRDİLER
        System.out.println("Günü Seçiniz (1-Pzt, ... 7-Paz): ");
        int gun = scanner.nextInt();

        System.out.println("Saati Giriniz (Örn: 14, 12, 11): ");
        int saat = scanner.nextInt();

        System.out.println("Yaşınızı Giriniz: ");
        int yas = scanner.nextInt();

        System.out.println("Meslek Seçimi (1: Öğrenci, 2: Öğretmen, 3: Diğer): ");
        int meslek = scanner.nextInt();

        System.out.println("Film Türü (1: 2D, 2: 3D, 3: IMAX, 4: 4DX): ");
        int filmTuru = scanner.nextInt();

        // HESAPLAMA ZİNCİRİ
        double tabanFiyat = calculateBasePrice(gun, saat);
        double ekstraUcret = getFormatExtra(filmTuru);
        double indirimOrani = calculateDiscount(yas, meslek, gun);
        double finalFiyat = calculateFinalPrice(tabanFiyat, ekstraUcret, indirimOrani);

        // SONUÇ
        generateTicketInfo(tabanFiyat, ekstraUcret, indirimOrani, finalFiyat, gun);
        
        scanner.close();
    }

    // --- ZORUNLU METOTLAR ---

    // 1. Hafta sonu kontrolü (Cumartesi: 6, Pazar: 7)
    public static boolean isWeekend(int gun) {
        return (gun == 6 || gun == 7);
    }

    // 2. Matine kontrolü (Saat 12:00 öncesi)
    public static boolean isMatinee(int saat) {
        return saat < 12;
    }

    // 3. Taban Fiyat Hesaplama
    public static double calculateBasePrice(int gun, int saat) {
        boolean haftaSonu = isWeekend(gun);
        boolean matine = isMatinee(saat);

        if (haftaSonu) {
            return matine ? 55.0 : 85.0; // Hafta sonu: Matine 55, Normal 85
        } else {
            return matine ? 45.0 : 65.0; // Hafta içi: Matine 45, Normal 65
        }
    }

    // 4. Format Ekstra Ücreti (Switch-Case Zorunlu)
    public static double getFormatExtra(int filmTuru) {
        double ekstra = 0;
        switch (filmTuru) {
            case 1: ekstra = 0; break;  // 2D
            case 2: ekstra = 25; break; // 3D
            case 3: ekstra = 35; break; // IMAX
            case 4: ekstra = 50; break; // 4DX
            default: 
                System.out.println("Hatalı film türü! Ekstra ücret 0 alındı.");
        }
        return ekstra;
    }

    // 5. İndirim Hesaplama (En yüksek indirim uygulanır mantığı)
    public static double calculateDiscount(int yas, int meslek, int gun) {
        double indirim = 0.0;

        // Meslek İndirimleri (Switch-Case Zorunlu)
        switch (meslek) {
            case 1: // Öğrenci
                // Pzt(1) - Prş(4): %20, Cum(5) - Paz(7): %15
                if (gun >= 1 && gun <= 4) {
                    indirim = 0.20;
                } else {
                    indirim = 0.15;
                }
                break;
            case 2: // Öğretmen
                // Sadece Çarşamba (3): %35
                if (gun == 3) {
                    indirim = 0.35;
                }
                break;
            case 3: // Diğer
                indirim = 0.0;
                break;
        }

        // Yaş İndirimleri (Eğer yaş indirimi meslek indiriminden yüksekse onu kullan)
        if (yas >= 65) {
            if (0.30 > indirim) indirim = 0.30;
        } else if (yas < 12) {
            if (0.25 > indirim) indirim = 0.25;
        }

        return indirim;
    }

    // 6. Final Fiyat Hesaplama
    public static double calculateFinalPrice(double taban, double ekstra, double indirimOrani) {
        // İndirim genellikle toplam tutar üzerinden veya sadece bilet üzerinden düşülür.
        // Burada toplam (Taban + Ekstra) üzerinden indirim yapılmıştır.
        double toplam = taban + ekstra;
        return toplam - (toplam * indirimOrani);
    }

    // 7. Bilet Bilgisi Yazdırma
    public static void generateTicketInfo(double taban, double ekstra, double indirim, double sonFiyat, int gun) {
        System.out.println("\n--- BİLET DETAYI ---");
        
        // Gün ismini yazdırmak için switch (Görsellik için ekledim)
        String gunIsmi = "";
        switch (gun) {
            case 1: gunIsmi = "Pazartesi"; break;
            case 2: gunIsmi = "Salı"; break;
            case 3: gunIsmi = "Çarşamba"; break;
            case 4: gunIsmi = "Perşembe"; break;
            case 5: gunIsmi = "Cuma"; break;
            case 6: gunIsmi = "Cumartesi"; break;
            case 7: gunIsmi = "Pazar"; break;
            default: gunIsmi = "Geçersiz Gün";
        }

        System.out.println("Gün: " + gunIsmi);
        System.out.println("Taban Fiyat: " + taban + " TL");
        System.out.println("Teknoloji Ekstra: " + ekstra + " TL");
        System.out.println("Uygulanan İndirim: %" + (int)(indirim * 100));
        System.out.println("-------------------------");
        System.out.println("ÖDENECEK TUTAR: " + sonFiyat + " TL");
    }
}

