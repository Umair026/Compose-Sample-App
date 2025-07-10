package com.umair.features.dashboard.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.umair.features.dashboard.R

//@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Dashboard(onItemClick: (User) -> Unit) {
    Column {
        Image(
            painter = painterResource(id = R.drawable.logo_image),
            contentDescription = ""
        )
        LazyColumn(
            content = {
                items(getUsers()) { user ->
                    UserView(
                        user = user,
                        onClick = onItemClick
                    )
                }
            },
            modifier = Modifier.padding(0.dp, 20.dp,0.dp,0.dp)
        )
    }

}

data class User(val image: Int, val name: String, val email: String)

@Composable
fun UserView(user: User, onClick: (User) -> Unit) {
    Card(
        modifier = Modifier.padding(8.dp)
            .fillMaxWidth()
            .clickable { onClick(user) }
    ) {
        Row (
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.size(48.dp)
                    .weight(0.2f),
                painter = painterResource(id = R.drawable.logo_image),
                contentDescription = "",
            )

            Column(
                modifier = Modifier.weight(0.8f)
            ) {
                Text(
                    text = user.name ,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = user.email,
                    style = MaterialTheme.typography.bodySmall,
                )
            }
        }
    }
}

fun getUsers() : MutableList<User> {
    var users = mutableListOf<User>()
    users.add(User(R.drawable.logo_image, "Umair", "charleswfairbanks@myownpersonaldomain.com"))
    users.add(User(R.drawable.logo_image, "Alice Smith", "alice.smith1@example.com"))
    users.add(User(R.drawable.logo_image, "Bob Jones", "bob.jones2@email.net"))
    users.add(User(R.drawable.logo_image, "Charlie Williams", "charlie.williams3@test.org"))
    users.add(User(R.drawable.logo_image, "Diana Brown", "diana.brown4@domain.io"))
    users.add(User(R.drawable.logo_image, "Edward Davis", "edward.davis5@mail.co"))
    users.add(User(R.drawable.logo_image, "Fiona Miller", "fiona.miller6@example.com"))
    users.add(User(R.drawable.logo_image, "George Wilson", "george.wilson7@email.net"))
    users.add(User(R.drawable.logo_image, "Hannah Moore", "hannah.moore8@test.org"))
    users.add(User(R.drawable.logo_image, "Ian Taylor", "ian.taylor9@domain.io"))
    users.add(User(R.drawable.logo_image, "Julia Anderson", "julia.anderson10@mail.co"))
    users.add(User(R.drawable.logo_image, "Kevin Thomas", "kevin.thomas11@example.com"))
    users.add(User(R.drawable.logo_image, "Laura Jackson", "laura.jackson12@email.net"))
    users.add(User(R.drawable.logo_image, "Michael White", "michael.white13@test.org"))
    users.add(User(R.drawable.logo_image, "Nora HaRis", "nora.haRis14@domain.io"))
    users.add(User(R.drawable.logo_image, "Oscar Martin", "oscar.martin15@mail.co"))
    users.add(User(R.drawable.logo_image, "Penelope Thompson", "penelope.thompson16@example.com"))
    users.add(User(R.drawable.logo_image, "Quentin Garcia", "quentin.garcia17@email.net"))
    users.add(User(R.drawable.logo_image, "Rachel Martinez", "rachel.martinez18@test.org"))
    users.add(User(R.drawable.logo_image, "Samuel Robinson", "samuel.robinson19@domain.io"))
    users.add(User(R.drawable.logo_image, "Tina Clark", "tina.clark20@mail.co"))
    users.add(User(R.drawable.logo_image, "Ulysses Rodriguez", "ulysses.rodriguez21@example.com"))
    users.add(User(R.drawable.logo_image, "Victoria Lewis", "victoria.lewis22@email.net"))
    users.add(User(R.drawable.logo_image, "Walter Lee", "walter.lee23@test.org"))
    users.add(User(R.drawable.logo_image, "Xenia Walker", "xenia.walker24@domain.io"))
    users.add(User(R.drawable.logo_image, "Yannick Hall", "yannick.hall25@mail.co"))
    users.add(User(R.drawable.logo_image, "Zoe Allen", "zoe.allen26@example.com"))
    users.add(User(R.drawable.logo_image, "Arthur Young", "arthur.young27@email.net"))
    users.add(User(R.drawable.logo_image, "Bella King", "bella.king28@test.org"))
    users.add(User(R.drawable.logo_image, "Chris Wright", "chris.wright29@domain.io"))
    return users
}