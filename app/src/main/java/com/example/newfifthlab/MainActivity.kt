package com.example.newfifthlab

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.asLiveData
import kotlin.math.roundToInt
import kotlin.random.Random

fun AddStudents(db: DataBaseClass)
{
    var students_to_add: MutableList<Student> = arrayListOf()
    val names: List<String> = listOf<String>("Александр", "Сергей", "Владимир", "Николай", "Алексей", "Виктор", "Андрей", "Юрий", "Михаил",
        "Дмитрий", "Анатолий", "Евгений", "Игорь", "Иван", "Валерий", "Вячеслав", "Олег", "Павел", "Борис", "Геннадий", "Константин", "Максим", "Виталий", "Пётр", "Валентин",
        "Анна", "Мария", "Виктория", "Екатерина", "Александра", "Алиса", "Полина", "Дарья", "София", "Любовь", "Наталья", "Ирина", "Ангелина", "Алёна", "Юлия", "Ксения",
        "Кристина", "Диана", "Валерия", "Анастасия", "Юлианна", "Кира", "Оксана", "Регина", "Карина")
    for(qwe in 0..20) {
        var choosing_name: String = names.get(Random.nextInt(0, 50))
        var choosing_weight: Int = Random.nextInt(45, 115)
        var choosing_height: Int = Random.nextDouble(150.0, 210.0).roundToInt()
        var choosing_age: Int = Random.nextInt(17, 25)
        var new_student =
            Student(qwe, choosing_name, choosing_weight, choosing_height, choosing_age)
        students_to_add.add(new_student)
    }
    //val news = students_to_add.sortedBy { it.weight }
    Thread{
    db.getDao().insertStudent(students_to_add)}.start()
}

class MainActivity : AppCompatActivity() {
    fun addRow(student_name: String, student_age: Int, student_height: Int, student_weight: Int) {
        val tableLayout: TableLayout = findViewById(R.id.table);
        val inflater: LayoutInflater = LayoutInflater.from(this);
        val tr: TableRow = inflater.inflate(R.layout.tablerow_inflate_layout, null) as TableRow
        var tv: TextView = tr.findViewById(R.id.row_name);
        tv.setText(student_name);
        tv = tr.findViewById(R.id.row_age);
        tv.setText(Integer.toString(student_age));
        tv = tr.findViewById(R.id.row_height);
        tv.setText(Integer.toString(student_height));
        tv = tr.findViewById(R.id.row_weight);
        tv.setText(Integer.toString(student_weight));
        tableLayout.addView(tr)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //val text_view_field: TextView = findViewById(R.id.textviewnew)
        setContentView(R.layout.activity_main)
        val db = DataBaseClass.getDataBase(this)
        //val text_view_field: TextView = findViewById()
        db.getDao().getAllStudents().asLiveData().observe(this){
            //text_view_field.text = ""
            val news = it.sortedBy { it.age }
            news.forEach{
                addRow(it.name, it.age, it.height, it.weight)
                //val text = "Id: ${it.id} Name: ${it.name} Age: ${it.age} Height: ${it.height} Weight: ${it.weight}\n"
                //text_view_field.append(text)
            }
        }
        AddStudents(db)
    }

    override fun onDestroy() {
        super.onDestroy()
        val db = DataBaseClass.getDataBase(this)
        Thread {
            db.getDao().deleteAllStudents()
        }.start()
    }
}