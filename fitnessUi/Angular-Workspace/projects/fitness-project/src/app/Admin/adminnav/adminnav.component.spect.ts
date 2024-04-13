import { ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { AppComponent } from './app.component';

describe('AppComponent', () => {
  let component: AppComponent;
  let fixture: ComponentFixture<AppComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AppComponent],
      imports: [RouterTestingModule]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AppComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create the app', () => {
    expect(component).toBeTruthy();
  });

  it('should render navigation links', () => {
    const compiled = fixture.nativeElement;
    expect(compiled.querySelector('.navbar')).toBeTruthy();
    expect(compiled.querySelectorAll('.navbar ul li').length).toBe(7); // Adjust the count based on your actual number of links
  });

  it('should navigate to admin page', () => {
    const compiled = fixture.nativeElement;
    const adminLink = compiled.querySelector('.admin');
    adminLink.click();
    fixture.detectChanges();
    expect(location.pathname).toBe('/admin');
  });

  // Add similar tests for other navigation links

});
    