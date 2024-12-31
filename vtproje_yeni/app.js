// // app.js

// // Sayfa yüklendikten sonra çalışacak kod
// document.addEventListener('DOMContentLoaded', function () {
//     // Sayfaya özgü işlevleri yükle
//     if (document.body.id === 'job-entries-page') {
//         initializeJobEntriesPage();
//     } else if (document.body.id === 'login-page') {
//         initializeLoginPage();
//     } else if (document.body.id === 'payments-page') {
//         initializePaymentsPage();
//     } else if (document.body.id === 'register-page') {
//         initializeRegisterPage();
//     }
// });

// // ** Job Entries Page İşlemleri**
// function initializeJobEntriesPage() {
//     const jobEntries = [
//         {id: 1, employeeName: 'Ahmet Yılmaz', title: 'Kıdemli Yazılım Geliştirici', department: 'IT', startDate: '2020-01-15', endDate: null},
//         {id: 2, employeeName: 'Ayşe Demir', title: 'İK Uzmanı', department: 'İK', startDate: '2019-03-01', endDate: '2022-12-31'}
//     ];
    
//     renderJobEntries(jobEntries);
    
//     // Filtreleme işlevlerini başlat
//     document.getElementById('titleFilter').addEventListener('input', filterEntries);
//     document.getElementById('departmentFilter').addEventListener('change', filterEntries);
//     document.getElementById('startDate').addEventListener('change', filterEntries);
//     document.getElementById('endDate').addEventListener('change', filterEntries);
// }

// function renderJobEntries(data) {
//     const tbody = document.getElementById('jobEntriesTableBody');
//     tbody.innerHTML = '';
    
//     data.forEach(entry => {
//         const row = `
//             <tr onclick="showDetails(${entry.id})">
//                 <td>${entry.employeeName}</td>
//                 <td>${entry.title}</td>
//                 <td>${entry.department}</td>
//                 <td>${new Date(entry.startDate).toLocaleDateString('tr-TR')}</td>
//                 <td>${entry.endDate ? new Date(entry.endDate).toLocaleDateString('tr-TR') : 'Devam Ediyor'}</td>
//             </tr>
//         `;
//         tbody.innerHTML += row;
//     });
// }

// function filterEntries() {
//     const titleFilter = document.getElementById('titleFilter').value.toLowerCase();
//     const departmentFilter = document.getElementById('departmentFilter').value;
//     const startDateFilter = document.getElementById('startDate').value;
//     const endDateFilter = document.getElementById('endDate').value;

//     const filtered = jobEntries.filter(entry => {
//         const matchesTitle = entry.title.toLowerCase().includes(titleFilter);
//         const matchesDepartment = !departmentFilter || entry.department === departmentFilter;
//         const matchesDateRange = (!startDateFilter || entry.startDate >= startDateFilter) &&
//             (!endDateFilter || !entry.endDate || entry.endDate <= endDateFilter);
//         return matchesTitle && matchesDepartment && matchesDateRange;
//     });

//     renderJobEntries(filtered);
// }

// function showDetails(entryId) {
//     const entry = jobEntries.find(e => e.id === entryId);
//     alert(`Çalışan: ${entry.employeeName}
//     Pozisyon: ${entry.title}
//     Departman: ${entry.department}
//     Başlangıç: ${new Date(entry.startDate).toLocaleDateString('tr-TR')}
//     Bitiş: ${entry.endDate ? new Date(entry.endDate).toLocaleDateString('tr-TR') : 'Devam Ediyor'}`);
// }

// // ** Login Page İşlemleri**
// function initializeLoginPage() {
//     document.querySelector('.login-btn').addEventListener('click', handleLogin);
// }

// async function handleLogin() {
//     const username = document.getElementById('username').value;
//     const password = document.getElementById('password').value;

//     if (!validateForm()) return;

//     const loginBtn = document.querySelector('.login-btn span');
//     const loader = document.getElementById('loader');
//     loginBtn.style.display = 'none';
//     loader.style.display = 'block';

//     try {
//         const response = await fetch('http://localhost:8080/calisanlar/login', {
//             method: 'POST',
//             headers: { 'Content-Type': 'application/json' },
//             body: JSON.stringify({ email: username, password: password }),
//         });

//         if (response.ok) {
//             const data = await response.json();
//             window.location.href = '/homepage.html';
//         } else {
//             alert('Giriş başarısız. Lütfen bilgilerinizi kontrol ediniz.');
//         }
//     } catch (error) {
//         console.error('Hata:', error);
//     } finally {
//         loginBtn.style.display = 'block';
//         loader.style.display = 'none';
//     }
// }

// // ** Register Page İşlemleri**
// function initializeRegisterPage() {
//     document.querySelector('.register-btn').addEventListener('click', handleRegister);
// }

// async function handleRegister() {
//     const username = document.getElementById('username').value;
//     const email = document.getElementById('email').value;
//     const password = document.getElementById('password').value;
//     const confirmPassword = document.getElementById('confirm-password').value;
//     const phone = document.getElementById('phone').value;

//     if (!validateRegisterForm()) return;

//     const registerBtn = document.querySelector('.register-btn span');
//     const loader = document.getElementById('loader');
//     registerBtn.style.display = 'none';
//     loader.style.display = 'block';

//     try {
//         const response = await fetch('http://localhost:8080/calisanlar/register', {
//             method: 'POST',
//             headers: { 'Content-Type': 'application/json' },
//             body: JSON.stringify({ username, email, password, phone }),
//         });

//         if (response.ok) {
//             alert('Kayıt başarılı!');
//             window.location.href = '/login.html';
//         } else {
//             alert('Kayıt başarısız.');
//         }
//     } catch (error) {
//         console.error('Hata:', error);
//     } finally {
//         registerBtn.style.display = 'block';
//         loader.style.display = 'none';
//     }
// }

// function validateRegisterForm() {
//     // Form doğrulama işlemleri
// }

// // ** Payments Page İşlemleri**
// function initializePaymentsPage() {
//     renderPayments(paymentData);
//     document.getElementById('searchInput').addEventListener('input', filterPayments);
//     document.getElementById('dateFilter').addEventListener('change', filterPayments);
// }

// function renderPayments(data) {
//     const tbody = document.getElementById('paymentsTableBody');
//     tbody.innerHTML = '';
//     data.forEach(payment => {
//         const row = `
//             <tr>
//                 <td>${new Date(payment.date).toLocaleDateString('tr-TR')}</td>
//                 <td>${payment.employee}</td>
//                 <td>${payment.amount.toLocaleString('tr-TR')} ₺</td>
//                 <td>${payment.method}</td>
//                 <td><span class="status ${payment.status}">${payment.status === 'completed' ? 'Tamamlandı' : 'Beklemede'}</span></td>
//             </tr>
//         `;
//         tbody.innerHTML += row;
//     });
// }

// function filterPayments() {
//     const searchTerm = document.getElementById('searchInput').value.toLowerCase();
//     const dateFilter = document.getElementById('dateFilter').value;

//     const filtered = paymentData.filter(payment => {
//         const matchesSearch = payment.employee.toLowerCase().includes(searchTerm);
//         const matchesDate = !dateFilter || payment.date === dateFilter;
//         return matchesSearch && matchesDate;
//     });

//     renderPayments(filtered);
// }
