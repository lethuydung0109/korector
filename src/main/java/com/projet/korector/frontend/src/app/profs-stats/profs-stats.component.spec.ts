import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfsStatsComponent } from './profs-stats.component';

describe('ProfsStatsComponent', () => {
  let component: ProfsStatsComponent;
  let fixture: ComponentFixture<ProfsStatsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProfsStatsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProfsStatsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
