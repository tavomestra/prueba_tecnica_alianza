import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { NgbDate, NgbDateStruct } from '@ng-bootstrap/ng-bootstrap';
import { NgxSpinnerService } from 'ngx-spinner';
import { ClienteService } from 'src/app/services/cliente.service';
import Swal, { SweetAlertIcon } from 'sweetalert2';
import { Cliente } from 'src/app/model/clientes';
import { Router } from '@angular/router';

@Component({
  selector: 'app-nuevo-cliente',
  templateUrl: './nuevo-cliente.component.html',
  styleUrls: ['./nuevo-cliente.component.css'],
})
export class NuevoClienteComponent implements OnInit {
  public form: FormGroup;
  submitted = false;

  constructor(
    private formBuilder: FormBuilder,
    private spinner: NgxSpinnerService,
    private clienteService: ClienteService,
    private router: Router,
  ) {}

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      name: new FormControl('', [Validators.required]),
      phone: new FormControl('', [
        Validators.required,
        Validators.pattern(/^[0-9]{10}$/),
      ]),
      email: new FormControl('', [
        Validators.required,
        Validators.pattern( /([\w\.\-_]+)?\w+@[\w-_]+(\.\w+){1,}/),
      ]),
      startDate: new FormControl('', { validators: [Validators.required] }),
      endDate: new FormControl('', { validators: [Validators.required] }),
    });
  }

  get formulario() {
    return this.form.controls;
  }

  validarFechas() {
    let erroresFechaInicio = {};
    let erroresFechaFinal = {};

    if (this.formulario.startDate.value && this.formulario.endDate.value) {
      const fechaIni: NgbDate = NgbDate.from(this.formulario.startDate.value);
      const fechaFin: NgbDate = NgbDate.from(this.formulario.endDate.value);

      if (fechaIni.after(fechaFin)) {
        erroresFechaInicio['outRange1'] =
          'La fecha inicio debe ser menor a la fecha fin';
      } else {
        this.formulario.startDate.setErrors(null);
      }

      if (fechaFin.before(fechaIni)) {
        erroresFechaFinal['outRange1'] =
          'La fecha fin no puede ser menor a la fecha inicio';
      } else {
        this.formulario.endDate.setErrors(null);
      }
    }
    for (const key in erroresFechaInicio) {
      this.formulario.startDate.setErrors(erroresFechaInicio);
    }

    for (const key in erroresFechaFinal) {
      this.formulario.endDate.setErrors(erroresFechaFinal);
    }
  }

  create() {
    this.submitted = true;
    if (this.form.valid) {
      this.spinner.show();
      let cliente = {} as Cliente;

      cliente.name = this.formulario.name.value;
      cliente.phone = this.formulario.phone.value;
      cliente.email = this.formulario.email.value;
      cliente.startDate = this.dateFormat(this.formulario.startDate.value);
      cliente.endDate = this.dateFormat(this.formulario.endDate.value);

      this.clienteService.create(cliente).subscribe(
        (data) => {
          this.spinner.hide();
          this.modal("Se ha creado correctamente");
          this.router.navigateByUrl('home');
        },
        (error) => {
          this.spinner.hide();
          this.modal(error.error.message, 'error');
        }
      );
    }
  }

  private dateFormat(fecha: NgbDateStruct): string {
    return (
      fecha.year +
      '-' +
      (fecha.month <= 9 ? '0' + fecha.month : fecha.month) +
      '-' +
      (fecha.day <= 9 ? '0' + fecha.day : fecha.day)
    );
  }

  modal(mensaje: string, icon: SweetAlertIcon = 'success') {
    Swal.fire({
      icon: icon,
      text: mensaje,
    });
  }
}
