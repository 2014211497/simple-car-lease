import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SimplecarleaseComponent } from './simplecarlease.component';

describe('SimplecarleaseComponent', () => {
  let component: SimplecarleaseComponent;
  let fixture: ComponentFixture<SimplecarleaseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SimplecarleaseComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SimplecarleaseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
