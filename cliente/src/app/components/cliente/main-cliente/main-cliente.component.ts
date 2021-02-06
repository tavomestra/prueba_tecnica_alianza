import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';
import { Cliente } from 'src/app/model/clientes';
import { ClienteService } from '../../../services/cliente.service';
import { AngularCsv } from 'angular7-csv/dist/Angular-csv';
import Swal, { SweetAlertIcon } from 'sweetalert2';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { NgbDateStruct } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-main-cliente',
  templateUrl: './main-cliente.component.html',
  styleUrls: ['./main-cliente.component.css'],
})
export class MainClienteComponent implements OnInit {
  public form: FormGroup;
  submitted = false;

  listClientes: Cliente[] = [];

  csvOptions = {
    fieldSeparator: ',',
    quoteStrings: '"',
    decimalseparator: '.',
    showLabels: false,
    showTitle: false,
    title: '',
    useBom: true,
    noDownload: false,
    headers: ['SharedKey', 'BusinessId', 'Email', 'Phone', 'DateAdded'],
  };

  constructor(
    private clienteService: ClienteService,
    private spinner: NgxSpinnerService,
    private router: Router,
    private formBuilder: FormBuilder
  ) {}

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      name: new FormControl(''),
      phone: new FormControl('', [Validators.pattern(/^[0-9]{10}$/)]),
      email: new FormControl('', [
        Validators.pattern(/([\w\.\-_]+)?\w+@[\w-_]+(\.\w+){1,}/),
      ]),
      startDate: new FormControl(''),
      endDate: new FormControl(''),
    });
    this.getAllClients();
  }

  get formulario() {
    return this.form.controls;
  }

  newClient() {
    this.router.navigateByUrl('nuevo-cliente');
  }

  /**
   * Obtener todos los clientes.
   */
  getAllClients() {
    this.spinner.show();
    this.listClientes = [];
    this.clienteService.getAll().subscribe((data) => {
      this.listClientes = data;
      this.spinner.hide();
    });
  }

  getClientByShredKey(sharedKey: string) {
    if (sharedKey) {
      this.spinner.show();
      this.listClientes = [];
      this.clienteService.getBySharedKey(sharedKey).subscribe(
        (data) => {
          this.listClientes.push(data);
          this.spinner.hide();
        },
        (error) => {
          this.modal(error.error.message, 'error');
          this.spinner.hide();
        }
      );
    } else {
      this.modal('SharedKey es obligatorio', 'warning');
    }
  }

  modal(mensaje: string, icon: SweetAlertIcon = 'success') {
    Swal.fire({
      icon: icon,
      text: mensaje,
    });
  }

  export() {
    this.spinner.show();
    new AngularCsv(this.listClientes, 'Clientes', this.csvOptions);
    this.spinner.hide();
  }

  advanceSearch() {
    this.submitted = true;
    let cliente = {} as Cliente;

    cliente.name = this.formulario.name.value;
    cliente.phone = this.formulario.phone.value;
    cliente.email = this.formulario.email.value;
    cliente.startDate = this.dateFormat(this.formulario.startDate.value);
    cliente.endDate = this.dateFormat(this.formulario.endDate.value);

    if (
      cliente.name ||
      cliente.phone ||
      cliente.email ||
      cliente.startDate ||
      cliente.endDate
    ) {
      this.spinner.show();
      this.listClientes = [];
      this.clienteService.getByFilters(cliente).subscribe(
        (data) => {
          this.listClientes = data;
          this.spinner.hide();
        },
        (error) => {
          this.spinner.hide();
        }
      );
    } else {
      this.modal('Ingrese un dato', 'warning');
    }
  }

  private dateFormat(fecha: NgbDateStruct): string {
    if (!fecha) {
      return null;
    }
    return (
      fecha.year +
      '-' +
      (fecha.month <= 9 ? '0' + fecha.month : fecha.month) +
      '-' +
      (fecha.day <= 9 ? '0' + fecha.day : fecha.day)
    );
  }
}
