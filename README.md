# Lab 2 – Thiết kế giao diện phẳng XML & ViewBinding

- **Sinh viên:** Kiều Bảo Giang – **MSSV:** 2474802010095
- **Môn:** Lập trình di động
- **Package:** `vn.edu.vlu.lab2` · Kotlin · minSdk 24 · targetSdk 37

Ứng dụng gồm hai màn hình **Đăng nhập** và **Hồ sơ người dùng**, dựng hoàn toàn bằng
`ConstraintLayout` phẳng và truy cập View bằng **ViewBinding** (không dùng `findViewById`).

## Ảnh chụp màn hình

| Đăng nhập | Profile | Layout Inspector |
|---|---|---|
| ![Login](screenshots/login.png) | ![Profile](screenshots/profile.png) | ![Layout Inspector](screenshots/layout_inspector.png) |

| Xoay ngang | Tablet (sw600dp) |
|---|---|
| ![Landscape](screenshots/login_land.png) | ![Tablet](screenshots/login_tablet.png) |

## Đối chiếu yêu cầu

| Mã | Yêu cầu | Thực hiện |
|---|---|---|
| R1 | Logo, tiêu đề, Email, Mật khẩu, nút Đăng nhập, dòng trạng thái | `res/layout/activity_login.xml` – chuỗi neo dọc `imgLogo → tvTitle → tilEmail → tilPassword → cbShowPassword → btnLogin → tvStatus → tvForgotPassword` |
| R2 | Để trống / email sai / mật khẩu < 6 ký tự | `LoginActivity.handleLogin()` dùng `when`; lỗi hiện ngay dưới ô nhập (`TextInputLayout.error`) |
| R3 | Đăng nhập hợp lệ ⇒ “Đăng nhập thành công: &lt;email&gt;” | Xác thực giả lập, sau đó mở `ProfileActivity` và truyền email qua `Intent` |
| R4 | Avatar tròn 1:1, tên, vai trò, các dòng nhãn \| giá trị, 2 nút chia đều | `res/layout/activity_profile.xml` – `ShapeableImageView` + `dimensionRatio 1:1` + `width_percent 0.35`, chain ngang `spread` + `weight 1` |
| R5 | ConstraintLayout phẳng + ViewBinding, chuỗi trong `strings.xml` | `buildFeatures { viewBinding = true }`, không có `findViewById`, không có chuỗi viết cứng |
| R6 | Nộp qua GitHub công khai | Repo `Lab2_Layout_ViewBinding_2474802010095` |

## Bài tập vận dụng

**Cấp 1**
- [x] Dòng “Quên mật khẩu?” căn giữa dưới `tvStatus`, nhấn vào hiện Toast.
- [x] Toàn bộ chuỗi nằm trong `strings.xml`; bản tiếng Anh ở `res/values-en/strings.xml`.

**Cấp 2**
- [x] `res/layout-land/activity_login.xml`: Guideline dọc 40%, logo + tiêu đề bên trái, form bên phải.
  Thêm `res/layout-land/activity_profile.xml` (avatar bên trái, thông tin bên phải).
- [x] CheckBox “Hiện mật khẩu” đổi `transformationMethod` của `edtPassword`.
- [x] Profile có thêm dòng “Số điện thoại”; `Barrier` giữ cột giá trị luôn nằm sau nhãn dài nhất.

**Cấp 3**
- [x] `TextInputLayout` + `TextInputEditText` (Material 3), mật khẩu có biểu tượng con mắt (`app:endIconMode="password_toggle"`).
- [x] Layout được bọc trong `ScrollView`; app chạy edge-to-edge nên `WindowInsets.kt` chừa chỗ cho thanh hệ thống **và bàn phím** – form không bị che khi gõ.
- [x] `res/layout-sw600dp/activity_login.xml`: form rộng tối đa 400dp (`layout_constraintWidth_max`), căn giữa màn hình.

## Cấu trúc chính

```
app/src/main/
├── java/vn/edu/vlu/lab2/
│   ├── LoginActivity.kt        # ViewBinding + kiểm tra dữ liệu + chuyển màn hình
│   ├── ProfileActivity.kt      # nhận email qua Intent, nút Đăng xuất = finish()
│   └── WindowInsets.kt         # padding cho status bar / navigation bar / bàn phím
└── res/
    ├── layout/                 # điện thoại dọc
    ├── layout-land/            # điện thoại ngang
    ├── layout-sw600dp/         # tablet
    ├── values/strings.xml      # tiếng Việt (mặc định)
    └── values-en/strings.xml   # tiếng Anh
```

## Kiểm thử

| Tình huống | Dữ liệu | Kết quả mong đợi |
|---|---|---|
| Để trống | Email rỗng hoặc mật khẩu rỗng | Toast “Vui lòng nhập email và mật khẩu”, trạng thái báo thiếu dữ liệu |
| Email sai | `abc@` / `123456` | Lỗi “Email không hợp lệ” dưới ô Email |
| Mật khẩu ngắn | `sv@vlu.edu.vn` / `123` | Lỗi “Mật khẩu tối thiểu 6 ký tự” |
| Hợp lệ | `sv@vlu.edu.vn` / `123456` | “Đăng nhập thành công: sv@vlu.edu.vn”, mở Profile hiển thị email |

Ma trận responsive: Pixel 4 (5”), Pixel 8 Pro, xoay ngang, Pixel Tablet, cỡ chữ lớn nhất.

## Chạy project

1. Mở thư mục bằng Android Studio, đợi Gradle Sync.
2. Chọn AVD và nhấn **Run** (hoặc `./gradlew assembleDebug`).
