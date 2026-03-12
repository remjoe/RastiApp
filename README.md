# Rästiapp
[Esittelyvideo](https://youtu.be/AWlhP8go3rE)
## Selitykset
### TaskViewModel
TaskViewModel hallitsee sovelluksen tilaa. Se pitää sisällään tehtävälistan "tasks" ja API:sta haetun tekstin "apiResult". ViewModel tarjoaa funktiot tehtävän lisäämiseen ja tilan vaihtamiseen (tehty/ei tehty).

### Compose selitys
UI käyttää "mutableStateOf" tilaa ViewModelissa. Kun tila muuttuu (esim. lisätään tehtävä), Compose havaitsee muutoksen automaattisesti ja piirtää muuttuneet osat uudelleen. Tätä sanotaan recompositioniksi.

### API kutsu sovelluksessa
Sovellus tekee yhden HTTP GET kutsun osoitteeseen `https://jsonplaceholder.typicode.com/todos/1`. Kutsu tehdään Retrofit-kirjastolla ja vastaus on JSON muodossa, josta luetaan "title" ja näytetään se UI:ssa. JSONPlaceholder on mock-API eli se palauttaa esimerkkidataa.
