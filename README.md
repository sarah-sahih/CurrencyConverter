# CurrencyConverter

## Présentation

CurrencyConverter est une application Android développée en Kotlin permettant de convertir un montant d'une devise vers une autre.

## Fonctionnalités

- Sélection de la devise source et de la devise cible.
- Saisie du montant à l'aide d'un clavier numérique.
- Conversion entre plusieurs devises.
- Inversion des devises source et cible.
- Réinitialisation du montant avec la touche C.
- Affichage du résultat avec deux chiffres après la virgule.

## Devises disponibles

- USD : dollar américain
- EUR : euro
- TND : dinar tunisien
- GBP : livre sterling
- JPY : yen japonais
- CAD : dollar canadien

## Technologies utilisées

- Android Studio
- Kotlin
- XML pour l'interface utilisateur
- View Binding

## Logique de conversion

La conversion utilise l'euro comme devise de référence et des taux de change définis dans l'application. Ces taux sont fixes et ne représentent pas nécessairement les taux du marché en temps réel.

## Installation et exécution

1. Cloner ou télécharger le repository.
2. Ouvrir le projet dans Android Studio.
3. Attendre la synchronisation de Gradle.
4. Lancer l'application sur un émulateur ou un appareil Android compatible.

## Tests

Les tests réalisés portent sur les conversions suivantes :

- 120 USD vers EUR : 102,56 EUR
- 100 EUR vers TND : 340,00 TND
- 50 GBP vers USD : 67,24 USD
- 10 EUR vers EUR : 10,00 EUR

## Captures d'écran

Les captures d'écran de l'application et des tests seront disponibles dans le dossier `captures/`.
