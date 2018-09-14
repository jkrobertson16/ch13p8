/** Date.java
 * This class creates a Date object with attributes for month, day and
 *  year.  
 * Note:  This is not the same as java.util.Date
 */

public class Date {
   /** Static Array of valid months (in month index order) */
   private static final String[] VALID_MONTHS = new String[] {
      "January", "February", "March", "April", "May", "June",
      "July", "August", "September", "October", "November", "December"
   };

   /** Month, as a String, initially NULL */
   private String month; 

   private int day = 0;  // initially invalid
   private int year = 0;  // initially invalid

   /** Empty constructor.
    * Sets default month, day and year to Jan. 1, 2000
    */
   public Date( ) {
      month = VALID_MONTHS[0];
      day = 1;
      year = 2000;
   }

   /** Argument constructor.
    * @param monthInt - month as an int
    * @param day
    * @param year
    */
   public Date(int monthInt, int day, int year) {
      setDate(monthInt, day, year);
   }

   /** Argument constructor.
    * @param monthString - month as a String
    * @param day
    * @param year
    */
   public Date(String monthString, int day, int year) {
      setDate(monthString, day, year);
   }

   /** Argument constructor.  Sets Date to the first day of the year.
    * @param year - initialize this Date to the first day of this year.
    */
   public Date(int year) {
      setDate(year);
   }

   /** Argument constructor. 
    * @param aDate - Date to base this new Date instance on.
    *  If null, this date will have no valid month set.
    */
   public Date(Date aDate) {
      if (aDate != null) {
         month = aDate.getMonth();
         day = aDate.getDay();
         year = aDate.getYear();
      }
   }

   /** Method to set this Date to the first day of the given year.
    * @param year - initialize this Date to the first day of this year
    * @return boolean - TRUE if year was valid    
    */
   public boolean setDate(int year) {
      return setDate(VALID_MONTHS[0], 1, year);
   }

   /** Method to set the attributes for this Date instance.
    * @param monthInt - month as an int
    * @param day
    * @param year
    * @return boolean - TRUE if date entries were valid    
    */
   public boolean setDate(int monthInt, int day, int year) {
      return setDate(getMonthString(monthInt), day, year);
   }

   /** Method to set the attributes for this Date instance.
    * @param monthString - month as a String
    * @param day
    * @param year
    * @return boolean - TRUE if date was valid
    */
   public boolean setDate(String monthString, int day, int year) {
      if (dateOK(monthString, day, year)) {
         month = monthString;
         this.day = day;
         this.year = year;
         return true;
      }
      return false;
   }

   /** Method to validate and set the year for this Date
    * @param year - int
    */
   public void setYear(int year) {
      if(yearOk(year)) {
         this.year = year;
      }
   }

   /** Method to validate the year
    * @param year - int
    * @return boolean - TRUE if year was valid
    */   
   public boolean yearOk(int year) {
      // ITP 220 - validate year, assuming 1900 is the earliest 
      //  year possible
   }

   /** Method to validate and set the month for this Date
    * @param monthIndex - int - month index
    * @return boolean - TRUE if month was valid
    */
   public void setMonth(int monthIndex) {
      String monthStr = getMonthString(monthIndex);
      if (monthStr != null) {
         month = monthStr;
      }
   }

   /** Method to set the day for this Date after checking that it is
    *  valid for the month that is currently set.
    * @param day - valid day
    */
   public void setDay(int day) {
      if(dayOk(day)) {
         this.day = day;
      }
   }

   /** Method to validate the day of a month
    * @param day - int
    * @return boolean - TRUE if day was valid
    */   
   public boolean dayOk(int day) {
      return dayOk(day, month);
   }
   
   /** Method to validate the day of a month
    * @param day - int
    * @param month - month to validate this day against
    * @return boolean - TRUE if day was valid
    */   
   public boolean dayOk(int day, String month) {
      // ITP 220 - validate days for the month, assume February
      //  can have 28 days (do not worry about leap years)
   }

   /** Equals method to compare this Date to another Object. 
    *  @param other - Object being compared to 'this'
    *  @return - TRUE if other contains the same information as 'this'
    */
   public boolean equals(Date otherDate) {
      if (otherDate == null) {
         return false;
      } else {
         // check MONTH String
         if((month == null && otherDate.getMonth() != null) ||
            ((month != null) && !month.equals(otherDate.getMonth())) ) {
                  return false;
         }

         // check day and year ints
         return (day == otherDate.getDay()) &&
         (year == otherDate.getYear());
      }
   }

   /** Method to determine if this Date precedes another one.
    * @param otherDate - Date being compared
    * @return boolean - TRUE if this Date precedes otherDate
    */
   public boolean precedes(Date otherDate) {
      if(otherDate == null) 		
          return true;
          
      // check the conditions of precedes in order, returning false when
      //  1. Year is > otherDate's year
      //  2. Year = otherDate's year, but month > otherDate's month
      //  3. Year and month = otherDate's, but day > otherDates's day
      return (year < otherDate.getYear()) || 
               ((year == otherDate.getYear()) && 
              (getMonthIndex(month) < otherDate.getMonthIndex(otherDate.getMonth()))) || 
               ((year == otherDate.getYear()) && 
              (getMonthIndex(month) == otherDate.getMonthIndex(otherDate.getMonth()) && 
               (day < otherDate.getDay())));
   }

   /** Method to return whether all 3 parameters create a valid Date
    * @param monthInt 
    * @param dayInt
    * @param yearInt
    * @return boolean - TRUE if Date is valid
    */
   private boolean dateOK(int monthInt, int dayInt, int yearInt) {
      return dateOK(getMonthString(monthInt), dayInt, yearInt);
   }

   /** Method to return whether all 3 parameters create a valid Date
    * @param monthString
    * @param dayInt
    * @param yearInt
    * @return boolean - TRUE if Date is valid
    */
   private boolean dateOK(String monthString, int dayInt, int yearInt) {
      return (monthOk(monthString) && dayOk(dayInt, monthString) && 
            yearOk(yearInt));
   }

   /** Internal method to validate a month String.
    *  @param month - month String to validate.
    *  @return boolean - TRUE if the month is valid
    */
   private boolean monthOk(String month) {
      if(month == null) {
         return false;
      }

      return getMonthIndex(month) >= 0;
   }

   /** Internal method to convert a month index to its corresponding 
    *   month String.
    *  @param monthNumber - month index where month=1 is January and month=12 is December
    *  @return String - month as a String
    */
   private String getMonthString(int monthNumber) {
      if(monthNumber > 0 && monthNumber <= VALID_MONTHS.length) {
         // retrieve the month found at monthNumber-1 (1 for the
         //  0 index position (not a valid month index)
         return VALID_MONTHS[monthNumber-1];
      }
      return null;
   }

   /** Internal method to convert a month String to its corresponding 
    *   month index.
    *  @param monthStr - month String
    *  @return int - gets month index for the current month, 0 indicates
    *   that a valid month has not been set.
    */   
   public int getMonthIndex(String monthStr) {         
      // ITP 220 - retrieve valid month index for the given month String

      // if this date's month is not valid, return 0
      return 0;
   }

   public int getDay( ) {
      return day;
   }

   public int getYear( ) {
      return year;
   }

   public String getMonth() {
      return month;
   }

   public String toString( ) {
      return (month + " " + day + ", " + year);
   }
}
