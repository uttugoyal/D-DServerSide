import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplayProductStockComponent } from './display-product-stock.component';

describe('DisplayProductStockComponent', () => {
  let component: DisplayProductStockComponent;
  let fixture: ComponentFixture<DisplayProductStockComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DisplayProductStockComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DisplayProductStockComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
