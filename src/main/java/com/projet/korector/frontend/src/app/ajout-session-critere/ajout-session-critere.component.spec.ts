import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AjoutSessionCritereComponent } from './ajout-session-critere.component';

describe('AjoutSessionCritereComponent', () => {
  let component: AjoutSessionCritereComponent;
  let fixture: ComponentFixture<AjoutSessionCritereComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AjoutSessionCritereComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AjoutSessionCritereComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
