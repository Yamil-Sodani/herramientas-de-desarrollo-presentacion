// Funciones utilitarias del sistema

document.addEventListener('DOMContentLoaded', function() {
    // Inicializar tooltips de Bootstrap
    initializeTooltips();
    
    // Cargar funciones dinámicas
    setupFormValidation();
    setupTableFilters();
});

// Inicializar tooltips
function initializeTooltips() {
    const tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'));
    tooltipTriggerList.map(function (tooltipTriggerEl) {
        return new bootstrap.Tooltip(tooltipTriggerEl);
    });
}

// Validación de formularios
function setupFormValidation() {
    const forms = document.querySelectorAll('form');
    forms.forEach(form => {
        form.addEventListener('submit', function(e) {
            if (!form.checkValidity() === false) {
                e.preventDefault();
                e.stopPropagation();
            }
            form.classList.add('was-validated');
        }, false);
    });
}

// Filtro en tablas
function setupTableFilters() {
    const filterInputs = document.querySelectorAll('.table-filter');
    filterInputs.forEach(input => {
        input.addEventListener('keyup', function() {
            filterTable(this);
        });
    });
}

// Función para filtrar tabla
function filterTable(input) {
    const filter = input.value.toUpperCase();
    const table = input.closest('.table-container').querySelector('table');
    const rows = table.querySelectorAll('tbody tr');
    
    rows.forEach(row => {
        const text = row.textContent.toUpperCase();
        row.style.display = text.indexOf(filter) > -1 ? '' : 'none';
    });
}

// Confirmación de eliminación
function confirmDelete(message = '¿Está seguro de eliminar este registro?') {
    return confirm(message);
}

// Formatear moneda
function formatCurrency(value) {
    return '$/ ' + parseFloat(value).toFixed(2);
}

// Formatear fecha
function formatDate(dateString) {
    const options = { year: 'numeric', month: 'long', day: 'numeric' };
    return new Date(dateString).toLocaleDateString('es-ES', options);
}

// Mostrar notificación
function showNotification(message, type = 'info') {
    const alertDiv = document.createElement('div');
    alertDiv.className = `alert alert-${type} alert-dismissible fade show`;
    alertDiv.role = 'alert';
    alertDiv.innerHTML = `
        ${message}
        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
    `;
    
    const container = document.querySelector('.container-fluid');
    container.insertBefore(alertDiv, container.firstChild);
    
    // Auto cerrar después de 5 segundos
    setTimeout(() => {
        const closeButton = alertDiv.querySelector('.btn-close');
        if (closeButton) {
            closeButton.click();
        }
    }, 5000);
}

// Exportar tabla a CSV
function exportTableToCSV(filename = 'export.csv') {
    const table = document.querySelector('table');
    let csv = [];
    
    // Encabezados
    const headers = [];
    table.querySelectorAll('thead th').forEach(th => {
        headers.push(th.textContent);
    });
    csv.push(headers.join(','));
    
    // Datos
    table.querySelectorAll('tbody tr').forEach(tr => {
        const row = [];
        tr.querySelectorAll('td').forEach((td, index) => {
            // No incluir columna de acciones
            if (index < headers.length - 1) {
                row.push('"' + td.textContent.trim() + '"');
            }
        });
        csv.push(row.join(','));
    });
    
    // Descargar
    const csvContent = 'data:text/csv;charset=utf-8,' + csv.join('\n');
    const link = document.createElement('a');
    link.setAttribute('href', encodeURI(csvContent));
    link.setAttribute('download', filename);
    link.click();
}

// Imprimir página
function printPage() {
    window.print();
}

// Actualizar página
function refreshPage() {
    location.reload();
}

// Ir a atrás
function goBack() {
    window.history.back();
}

// Copiar al portapapeles
function copyToClipboard(text) {
    navigator.clipboard.writeText(text).then(function() {
        showNotification('Copiado al portapapeles', 'success');
    }).catch(function(err) {
        showNotification('Error al copiar', 'danger');
    });
}

// Buscar en tabla (Ctrl + F personalizado)
document.addEventListener('keydown', function(event) {
    if (event.ctrlKey && event.key === 'f') {
        event.preventDefault();
        const searchInput = prompt('Buscar en tabla:');
        if (searchInput) {
            const table = document.querySelector('table');
            if (table) {
                const rows = table.querySelectorAll('tbody tr');
                let found = 0;
                rows.forEach(row => {
                    const text = row.textContent.toUpperCase();
                    if (text.indexOf(searchInput.toUpperCase()) > -1) {
                        row.style.display = '';
                        found++;
                    } else {
                        row.style.display = 'none';
                    }
                });
                showNotification(`Se encontraron ${found} resultados`, 'info');
            }
        }
    }
});

// Funciones específicas del dashboard

// Actualizar datos del dashboard
function refreshDashboard() {
    showNotification('Actualizando datos...', 'info');
    setTimeout(() => {
        location.reload();
    }, 1500);
}

// Cargar gráficos (si está disponible Chart.js)
function initCharts() {
    if (typeof Chart === 'undefined') {
        console.log('Chart.js no está cargado');
        return;
    }
    // Los gráficos se inicializan directamente en dashboard.html
}

// Funciones de formulario

// Validar email
function validateEmail(email) {
    const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return re.test(email);
}

// Validar teléfono
function validatePhone(phone) {
    const re = /^[0-9\-\+\(\)]+$/;
    return re.test(phone);
}

// Validar documento
function validateDocument(doc) {
    return doc.length >= 6 && doc.length <= 20;
}

// Validación en tiempo real
document.addEventListener('DOMContentLoaded', function() {
    // Email
    document.querySelectorAll('input[type="email"]').forEach(input => {
        input.addEventListener('blur', function() {
            if (this.value && !validateEmail(this.value)) {
                this.classList.add('is-invalid');
                this.classList.remove('is-valid');
            } else if (this.value) {
                this.classList.add('is-valid');
                this.classList.remove('is-invalid');
            }
        });
    });
});

// Exportar funciones
console.log('Sistema Paul Car\\'s listo - v1.0.0');
